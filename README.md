### Authors : Conner Benitez, Taylor Curyto, Ashleigh Gossman, and Sarah Winne

## Fitness Tracker App
Fitness Tracker App, using MySQLWorkbench, Node.js, and Java GUI.

## Installation Instructions

### Database
*Note: If you already have MySQL Workbench, skip to Step 2.*

1.  Download MySQL Workbench from this link: https://dev.mysql.com/downloads/workbench/
2.  Open **MySQL Workbench**.
3.  On the welcome page ("Welcome to MySQL Workbench"), select the small plus (`+`) next to "MySQL Connections".
4.  On the prompt this brings up, enter a name and press **OK**.
5.  Open the connection and enter the password `root` for user `root`.
    * *Note: If you have a different user or password, log in with your credentials. Later, navigate through the folders `my_app` > `Backend` > `config` to the `db.config.js` file, and change the `user` and `password` fields from `root` to your specific credentials.*
6.  Go to the **Administration** tab on the left-hand side of the screen.
7.  Select **Data Import/Restore** under "Management".
8.  Select **Import from Self-Contained File**, and choose the file: `FitnessTrackerDatabase.sql` (This can be found in the GitHub repository under Code Submission).
9.  Press **Start Import**.
10. Go to the **Schemas** tab on the left-hand side.
11. If the schema `fitness_tracker` is not there, right-click under the schemas tab and select **Refresh All**.
12. Once the schema appears, the Database is set up.

### Node.js
1.  Download Node.js at: https://nodejs.org/en/download
2.  Follow the instructions to download and install it on your local system.
3.  Run `node` in your Command Prompt to verify proper installation.

### Running the Application Locally
1.  Navigate to the folder directory.
2.  Turn on the Database server in MySQL Workbench.
3.  Download Visual Studio Code (if not already installed): https://code.visualstudio.com/download
4.  In Visual Studio Code, choose to clone a git repository and enter the URL from the code submission.
5.  Navigate to the `backend` folder and run:
    * `npm install`
    * `npm start`
6.  Open a separate terminal and navigate (using `cd`) to wherever you placed the repository folder.
7.  Navigate into the `1.0` folder where the .jar file is.
8.  Type the following command:
    * `java -jar FitnessTracker-1.0.jar`
9.  The app should open.
