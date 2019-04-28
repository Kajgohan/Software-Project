#README

Description:
This api is for hospital emergency alerts and it uses the standard color codes.
When an alert is sent, it is added to the database and an email is sent.
There is also an employees database to store all the employees who can fulfill the tasks.
In the screen of the emergency table, you can select the employee to fulfill in the bottom right.
Here you can also open the image of that was taken when the emergency button was pressed.
In the screen of the employee table, you can select add and remove employees in the bottom right.

Variables needed for operation:
before running the api you must set the recipient:
    `Emergency.setRecipient("myEmail@gmail.com");`

also you can overwrite our test account with
    `Emergency.setSender("myAccount");`
    `Emergency.setSenderPassword("myPassword");`

You can initialize employes in the employees database by adding them to the employeesForEmergencyApi.csv.
This csv should be placed in the top level of the project.

If you overwrite our account you must make sure that your account has "Less secure app access"
    Go to https://myaccount.google.com/u/1/security
    Sign into the account
    Scroll down to Less secure app access and turn it on
    More details at https://support.google.com/mail/?p=InvalidSecondFactor


Run:
we take in the origin node which is meant to represent the node the kiosk is at
the sizing and style is up to you
in testing we used the parameters 50,50, 1500, 1000, null, "destNode", "originNode"

################################IMPORTANT###############################################################################
#   When changing a value in the employees editor, enter must be pressed to enter the value into the database.         #
#   THIS MEANS THAT CLICKING OFF OF IT WILL *NOT* UPDATE THE DATABASE                                                  #
########################################################################################################################

Details:
To add employees to the database, press the + in the bottom right of the employees screen
To remove employees from the database, press the - in the bottom right of the employees screen
Checking the IsFulfilled box will, by default, use the employee selected in the dropdown menu on the employees screen

# For Technical Support, Contact Team B - Blood Orange Bishopfish either in person with Jimmy Kajon or Carl Runci, 917-621-7826 or 301-310-3370, or at Bloodorangebishopfish@gmail.com #

Thank you for using our API! We love you :3
