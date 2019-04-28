package bishopfish.controllers;

import bishopfish.db.DBCustom;
import bishopfish.utils.FeatureTypeEntry;
import javafx.scene.Group;

import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

// CSI controller state information
public class CSI {
    // static variable single_instance of type Singleton
    private static CSI single_instance = null;

    private Stack<String> backStack;
    private String curFxml;
    private String curUser;     // username of the user logged in
    private String permissions; // _+ means admin in permissions
    private HpController hpController;
    private MasterController masterController;
    private FeatureTypeEntry fent; // for now just for identifying certain things, not a security field
    private Timer mementoTimer;
    private int mementoCountdown;
    private boolean mementoActive;



    // private constructor restricted to this class itself
    private CSI() {
        backStack = new Stack<>();
        curFxml = "";
        curUser = null;
        permissions = "";
    }

    // static method to create instance of Singleton class
    public static CSI getInstance() {
        if (single_instance == null) {
            single_instance = new CSI();
        }
        return single_instance;
    }

    public void resetCur() {
        curFxml = hpController.getFxml();
    }

    public void resetBackStack() {
        backStack = new Stack<>();
    }

    public Stack<String> getBackStack() {
        return backStack;
    }

    public void setBackStack(Stack<String> backStack) {
        this.backStack = backStack;
    }

    public String getCurFxml() {
        return curFxml;
    }

    public void setCurFxml(String curFxml) {
        this.curFxml = curFxml;
    }

    public String getCurUser() {
        return curUser;
    }

    public void setCurUser(String curUser) {
        this.curUser = curUser;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public HpController getHpController() {
        return hpController;
    }

    public void setHpController(HpController hpController) {
        this.hpController = hpController;
    }

    public MasterController getMasterController() {
        return masterController;
    }

    public void setMasterController(MasterController masterController) {
        this.masterController = masterController;
    }

    public FeatureTypeEntry getFent() {
        return fent;
    }

    public void setFent(FeatureTypeEntry fent) {
        this.fent = fent;
    }

    public Timer getMementoTimer() {
        return mementoTimer;
    }

    public void setMementoTimer(Timer mementoTimer, TimerTask task) {
        this.mementoTimer = mementoTimer;
        mementoCountdown = 30;
        mementoActive = false;
        mementoTimer.scheduleAtFixedRate(task, 0, 1000);
    }

    public int getMementoCountdown() {
        return mementoCountdown;
    }

    public void setMementoCountdown(int mementoCountdown) {
        this.mementoCountdown = mementoCountdown;
    }

    public boolean isMementoActive() {
        return mementoActive;
    }

    public void setMementoActive(boolean mementoActive) {
        this.mementoActive = mementoActive;
    }

    public void addToBackStack(String backPath) {
        // matches the previous or
        if (backPath.isEmpty() || backStack == null ||
                (!backStack.empty() && backPath.equals(backStack.peek()) ||
                (!backStack.empty() && curFxml.equals(backStack.peek()))    )) {
            return;
        }
        backStack.push(backPath);
    }

    public String popBackStack() {
        // matches the previous or
        if (backStack == null || backStack.empty()) {
            if (hpController != null) {
                return hpController.getFxml();
            } else {
                return "welcomeScreen.fxml";
            }
        }
        return backStack.pop();
    }

    public void load(String newPath) {
        if (!newPath.equals(curFxml)) {
            addToBackStack(curFxml);
        }
        setCurFxml(newPath);
    }

    public void clearAllAstar() {
        hpController.getDirectionsFxml().getChildren().clear();
        hpController.getDirectionsFxml().setVisible(false);
        hpController.getDirectionsFxml().setMouseTransparent(true);
        hpController.getScrollPane().setVisible(false);
        hpController.getScrollPane().setMouseTransparent(true);
        hpController.getToggleDirections().setVisible(false);

    }


    public boolean hasPermission(String fid) {
        String ptype = DBCustom.getFidToPtypeHM().get(fid);
        if (ptype.equals("R") && permissions.contains("_")) {
            return true;
        }
        if (ptype.equals("F") && permissions.contains("+")) {
            return true;
        }
        return permissions.contains(DBCustom.getPermissionsHM().get(fid));
    }

    @Override
    public String toString() {
        return String.format("\tBackStack: %d %s\n\tCurFxml: %s\n\tPermissions: %s (%s)", backStack.size(), backStack, curFxml, permissions, curUser);
    }

}
