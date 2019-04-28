package bishopfish.mapBuilder;

import bishopfish.controllers.MapBuilderDisplayController;
import bishopfish.map.Edge;
import bishopfish.map.EdgeEntry;
import bishopfish.map.Node;
import bishopfish.map.NodeEntry;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.effect.Bloom;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import jfxtras.scene.menu.CirclePopupMenu;
import javafx.event.EventHandler;

import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;

import static java.lang.String.valueOf;

public class GroupMapBuilder {
    MenuItem test;
    ImageView testGraphic;
    ArrayList<Group> allGroups;
    MapBuilderDisplayController controller;
    ArrayList<Node> listNodes;
    ArrayList<Edge> listEdges;
    Node primed;

    public void setPrimed(Node primed) {
        this.primed = primed;
    }

    public GroupMapBuilder(MapBuilderDisplayController controller, ArrayList<Group> groups, ArrayList<Node> listNodes, ArrayList<Edge> listEdges) {
        this.controller = controller;
        this.allGroups = groups;
        this.listEdges = listEdges;
        this.listNodes = listNodes;
        primed = null;
    }

    public void allNodes() {
        for (bishopfish.map.Node i : this.listNodes) {
            Circle circle = makeCircle(i.getXCoord() / 3, i.getYCoord() / 3, i);
            addActions(circle);
            i.setCircle(circle);
            this.allGroups.get(groupIndex(i.getFloor())).getChildren().addAll(circle);
        }

    }

    public void clearAndDrawAll() {
        for (Group i : this.allGroups) {
            i.getChildren().clear();
        }

        this.allNodes();
        this.allEdges();



    }

    public void clearAndDrawEdges() {
        for (Group i : allGroups) {
            for (javafx.scene.Node j : i.getChildren()) {
                if (j instanceof Line) {
                    i.getChildren().removeAll(j);

                }
            }
        }
        allEdges();
    }


    public void allEdges() {
        for (Edge i : this.listEdges) {
            Line line = new Line(i.getStart().getXCoord() / 3, i.getStart().getYCoord() / 3, i.getEnd().getXCoord() / 3, i.getEnd().getYCoord() / 3);
            line.startXProperty().bind(i.getStart().getCircle().centerXProperty());
            line.startYProperty().bind(i.getStart().getCircle().centerYProperty());
            line.endXProperty().bind(i.getEnd().getCircle().centerXProperty());
            line.endYProperty().bind(i.getEnd().getCircle().centerYProperty());
            line.setUserData(i);
            addActionsLine(line);
            this.allGroups.get(groupIndex(i.getStart().getFloor())).getChildren().add(0, line);
        }
    }

    private void addActionsLine(Line line) {
        line.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(controller.getAddOrDelete() == 2){
                    System.out.println("heheherhehrherhehr");
                    listEdges.remove((Edge)line.getUserData());
                    controller.getDbbEdge().remove(((Edge) line.getUserData()).getName());
                    clearAndDrawAll();
                }
            }
        });

    }

    private void addActions(Circle circle) {
        final double[] mouseX = new double[1];
        final double[] mouseY = new double[1];
        final double[] centerX = new double[1];
        final double[] centerY = new double[1];
        circle.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                    System.out.println("Click is being detected");
                    System.out.println(event.getSource());
                    event.consume();
                    mouseX[0] = event.getSceneX();
                    mouseY[0] = event.getSceneY();
                    System.out.println(mouseX[0]);
                    System.out.println(mouseY[0]);
                    centerX[0] = circle.getCenterX();
                    centerY[0] = circle.getCenterY();
                    System.out.println("center values of circle");
                    System.out.println(centerX[0]);
                    System.out.println(centerY[0]);

            }
        });

        circle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                circle.setRadius(4);
            }
        });

        circle.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!circle.getUserData().equals(primed)){
                    circle.setRadius(2);
                }
            }
        });

        circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                event.consume();
                if (controller.getAddOrDelete() == 2) {
                    ArrayList<Edge> temp = (ArrayList<Edge>)listEdges.clone();
                    for (Edge i: temp) {
                        if(circle.getUserData().equals(i.getEnd()) || circle.getUserData().equals(i.getStart())){
                            controller.getDbbEdge().remove(i.getName());
                            listEdges.remove(i);
                        }
                    }
                    listNodes.remove(circle.getUserData());
                    controller.getDbbNode().remove(((Node)circle.getUserData()).getName());
                    clearAndDrawAll();

                } else if(controller.getAddOrDelete() == 1){
                    if (event.getButton() == MouseButton.PRIMARY) {
                        if (primed == null) {
                            controller.setTextFiledsVisibility(true);
                            controller.setFilledID(valueOf(((Node) circle.getUserData()).getName()));
                            controller.setxCoordpromt(valueOf(((Node) circle.getUserData()).getXCoord()));
                            controller.setyCoordpromt(valueOf(((Node) circle.getUserData()).getYCoord()));
                            controller.setFloorFiledpromt(((Node) circle.getUserData()).getFloor());
                            controller.setNodeTypepromt(((Node) circle.getUserData()).getNodeType());
                            controller.setLongNamepromt(((Node) circle.getUserData()).getLongName());
                            controller.setShortNamepromt(((Node) circle.getUserData()).getShortName());
                            controller.setBuildingFieldpromt(((Node) circle.getUserData()).getBuilding());
                            controller.setFilledXCoord(valueOf(((Node) circle.getUserData()).getXCoord()));
                            controller.setFilledYCoord(valueOf(((Node) circle.getUserData()).getYCoord()));
                            controller.setFilledFloor(((Node) circle.getUserData()).getFloor());
                            controller.setFilledBuilding(((Node) circle.getUserData()).getBuilding());
                            controller.setFillednodeT(((Node) circle.getUserData()).getNodeType());
                            controller.setFilledShortN(((Node) circle.getUserData()).getShortName());
                            controller.setFilledLongN(((Node) circle.getUserData()).getLongName());
                            primed = (Node) circle.getUserData();
                            circle.setFill(Color.BLUE);
                        }


                        // TODO: 4/21/2019 set the color of the primed node to light blue (match the background)

                        //if left click and there is a primed node, then make an edge between them
                        else if (primed != null) {
                            System.out.println("add edge to new node");
                            System.out.println(event.getSource());
                            EdgeEntry addEdge = new EdgeEntry(primed.getName() + "_" + ((Node) circle.getUserData()).getName(), primed.getName(), ((Node) circle.getUserData()).getName());
                            listEdges.add(new Edge(addEdge, controller.getHmNodes()));
                            controller.getDbbEdge().add(addEdge);
                            clearAndDrawAll();
                            //sets the old primed back to black and makes new primed null
                            primed.getCircle().setFill(Color.BLACK);
                            primed = null;
                        }
                    }

            }else{}


            }
        });

        // TODO: 4/21/2019 add a on mouse hover action so , the nodes that are hovered over
        // TODO: 4/21/2019 change color or get outlined so the user knows that you are over the node

        circle.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent eventDrag) {
                if (controller.getAddOrDelete() == 1) {
                    double scalar = controller.getScale();
                    System.out.println("source during drag");
                    System.out.println(eventDrag.getSource());

                    eventDrag.consume();

                    System.out.println("eventDrag cords");
                    System.out.println(eventDrag.getSceneX());
                    System.out.println(eventDrag.getSceneY());

                    double x = centerX[0] + ((1 / scalar) * ((eventDrag.getSceneX() - mouseX[0])));
                    double y = centerY[0] + ((1 / scalar) * ((eventDrag.getSceneY() - mouseY[0])));

                    circle.setCenterX(x);
                    circle.setCenterY(y);

                    Node newNode1 = (Node) circle.getUserData();
                    newNode1.setCoord(new Point((int) x * 3, (int) y * 3));
                    listNodes.set(listNodes.indexOf(circle.getUserData()), newNode1);
                    ArrayList<String> newNode = new ArrayList<>();
                    newNode.add(String.valueOf((int) x * 3));
                    newNode.add(String.valueOf((int) y * 3));
                    newNode.add(controller.getCurrentFloor());
                    for (int i = 0; i < 4; i++) {
                        newNode.add(null);
                    }
                    controller.getDbbNode().update(((Node) circle.getUserData()).getName(), newNode);
                }
            }
        });

//        circle.setOnMouseDragReleased(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                event.consume();
//                clearAndDrawAll();
//            }
//        });



    }

    private Circle makeCircle(int xCord, int yCord, Node node) {
        Circle circle = new Circle(2);
        circle.setCenterX(xCord);
        circle.setCenterY(yCord);
        circle.setUserData(node);
        return circle;
    }

    public Node getPrimed() {
        return primed;
    }

    public void setListNodes(ArrayList<Node> listNodes) {
        this.listNodes = listNodes;
    }

    public ArrayList<Node> getListNodes() {
        return listNodes;
    }

    private int groupIndex(String floor) {
        if (floor.equals("3")) {
            return 0;
        } else if (floor.equals("2")) {
            return 1;
        } else if (floor.equals("1")) {
            return 2;
        } else if (floor.equals("G")) {
            return 3;
        } else if (floor.equals("L1")) {
            return 4;
        } else {
            return 5;
        }
    }

}
