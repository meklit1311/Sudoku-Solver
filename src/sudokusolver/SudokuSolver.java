package sudokusolver;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SudokuSolver extends Application{

    public static void main(String[] args) {
        launch(args);
    }
    
    // For reference purposes
    Stage stage;
    
    // All the scenes/stages
    Scene mainMenuScene;
    Stage difficultyStage;
    Scene playSudokuScene;
    Scene solveSudokuScene;
    Scene aboutUsScene;
    
    // All assets
    
    // Main Menu Assets
    RadioButton rdoPlayGame;
    RadioButton rdoSolveSudoku;
    RadioButton rdoAboutUs;
    
    Button btnSelect;
    Button btnClose;
    
    Text txtMenuDescription;
    
    // Play Sudoku difficulty assets
    RadioButton rdoEasy;
    RadioButton rdoMedium;
    RadioButton rdoHard;
    
    // Play Sudoku assets
    Sudoku playNumbers;
    TextField[][] playUserInput;
    Text txtPlayState;
    
    // Solve Sudoku assets
    TextField[][] solveUserInput;
    Text txtSolveState;
    int[][] solveNumbers;
    
    @Override
    public void start(Stage primaryStage) {
        
        // For reference purposes
        stage = primaryStage;
        
        // Make an Image object for icons and logos
        Image icon = new Image("MainLogo.png");
        
        // Stage width and height
        int stageWidth = 600;
        int stageHeight = 400;

        
        /**
         * MAIN MENU SCENE
         */
        
        // ---------- The title section ----------
        
        // ----- Set up the logo -----
        Image imgLogo = new Image("MainLogo.png");
        ImageView imgLogoView = new ImageView(imgLogo);
        
        // Set logo size
        imgLogoView.setFitWidth(100);
        imgLogoView.setFitHeight(100);
        
        // ----- Set up the title card -----
        Text txtTitle = new Text("Sudoku Solver!");
        Text txtSubTitle = new Text("...with the game");
        
        txtTitle.setFont(new Font(40));
        txtSubTitle.setFont(new Font(24));
        
        VBox bxTitleCard = new VBox(txtTitle, txtSubTitle);
        
        // ----- Finish up the title section -----
        HBox bxTitleSection = new HBox(30, imgLogoView, bxTitleCard);
        bxTitleSection.setPadding(new Insets(10));
        
        // ---------- The content section ----------
        
        // ----- Set up the three choices -----
        
        rdoPlayGame = new RadioButton();
        rdoSolveSudoku = new RadioButton();
        rdoAboutUs = new RadioButton();
        
        ToggleGroup rdoGroup = new ToggleGroup();
        
        rdoPlayGame.setToggleGroup(rdoGroup);
        rdoSolveSudoku.setToggleGroup(rdoGroup);
        rdoAboutUs.setToggleGroup(rdoGroup);
        
        rdoPlayGame.setOnAction(e -> rdoPlayGame_Click());
        rdoSolveSudoku.setOnAction(e -> rdoSolveSudoku_Click());
        rdoAboutUs.setOnAction(e -> rdoAboutUs_Click());
        
        rdoPlayGame.setSelected(true);
        
        Label lblPlayGame = new Label("Play a round of Sudoku");
        Label lblSolveSudoku = new Label("Solve a game of Sudoku");
        Label lblAboutUs = new Label("About us");
        
        lblPlayGame.setFont(new Font(15));
        lblSolveSudoku.setFont(new Font(15));
        lblAboutUs.setFont(new Font(15));

        // ----- Arrange the choices -----
        
        HBox bxPlayGame = new HBox(20, rdoPlayGame, lblPlayGame);
        HBox bxSolveSudoku = new HBox(20, rdoSolveSudoku, lblSolveSudoku);
        HBox bxAboutUs = new HBox(20, rdoAboutUs, lblAboutUs);
        
        
        VBox bxMenuChoices = new VBox(30, bxPlayGame, bxSolveSudoku, bxAboutUs);
        
        // ----- Set up the description panel -----
        
        txtMenuDescription = new Text();
        
        // Set to the default description
        rdoPlayGame_Click();
        
        // ----- Bring them together -----
        
        HBox bxContentSection = new HBox(30, bxMenuChoices, txtMenuDescription);
        bxContentSection.setPadding(new Insets(20));
        
        
        // ---------- The button section ----------
        
        Region rgnButtons = new Region();
        
        btnSelect = new Button("Select");
        btnClose = new Button("Close");
        
        btnSelect.setOnAction(e -> btnSelect_Click());
        btnClose.setOnAction(e -> btnClose_Click());
        
        btnSelect.setPrefWidth(80);
        btnClose.setPrefWidth(80);
        
        HBox bxButtonBox = new HBox(20, rgnButtons, btnSelect, btnClose);
        bxButtonBox.setHgrow(rgnButtons, Priority.ALWAYS);
        
        
        /**
         * PLAY SUDOKU DIFFICULTY STAGE
         */
        
        
        difficultyStage = new Stage();
        difficultyStage.initModality(Modality.APPLICATION_MODAL);
        difficultyStage.setTitle("Set Difficulty");
        difficultyStage.setMinWidth(250);
        
        Label lblDifficulty = new Label("Set the difficulty of your current session:");
        lblDifficulty.setFont(new Font(15));
        
        HBox bxLabel = new HBox(lblDifficulty);
        bxLabel.setPadding(new Insets(10, 0, 10, 0));
        
        rdoEasy = new RadioButton();
        rdoMedium = new RadioButton();
        rdoHard = new RadioButton();
        
        ToggleGroup tgDifficulty = new ToggleGroup();
        
        rdoMedium.setSelected(true);
        
        rdoEasy.setToggleGroup(tgDifficulty);
        rdoMedium.setToggleGroup(tgDifficulty);
        rdoHard.setToggleGroup(tgDifficulty);
        
        Label lblEasy = new Label("Easy");
        Label lblMedium = new Label("Medium");
        Label lblHard = new Label("Hard");
        
        HBox bxEasy = new HBox(10, rdoEasy, lblEasy);
        HBox bxMedium = new HBox(10, rdoMedium, lblMedium);
        HBox bxHard = new HBox(10, rdoHard, lblHard);
        
        VBox bxDifficulty = new VBox(20, bxEasy, bxMedium, bxHard);
        
        Button btnOK = new Button("Play!");
        btnOK.setOnAction(e -> btnPlay_Click());
        
        Button btnCancel = new Button("Cancel");
        btnCancel.setOnAction(e -> btnCancelPlay_Click());
        
        HBox promptPane = new HBox(20);
        promptPane.getChildren().addAll(btnOK, btnCancel);
        promptPane.setAlignment(Pos.CENTER);
        
        VBox mainDifficultyPane = new VBox(bxLabel, bxDifficulty, promptPane);
        mainDifficultyPane.setPadding(new Insets(10));
        
        Scene difficultyScene = new Scene(mainDifficultyPane, 300, 200);
        difficultyStage.setScene(difficultyScene);
        difficultyStage.setResizable(false);
        difficultyStage.getIcons().add(icon);
        
        
        /**
         * PLAY SUDOKU SCENE
         */
        
        
        playUserInput = new TextField[9][9];
        
        // Initialize the userInput
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                playUserInput[i][j] = new TextField();
                playUserInput[i][j].setPrefWidth(30);
            }
        }
        
        HBox[] playRows = new HBox[9];
        
        // Initialize the rows
        for (int i = 0; i < 9; i++) {
            playRows[i] = new HBox(5);
        }
        
        // Add the text input fields into the rows
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                playRows[i].getChildren().add(playUserInput[i][j]);
            }
        }
        
        VBox bxPlaySudokuBoard = new VBox(5);
        
        // Insert each row into the board
        for (int i = 0; i < 9; i++) {
            bxPlaySudokuBoard.getChildren().add(playRows[i]);
        }
        
        // ---------- Description section ----------
        
        txtPlayState = new Text();
        
        // ---------- Button section ----------
        
        HBox bxPlaySudokuButtons = new HBox(20);
        
        Region rgnPlaySudoku = new Region();
        
        Button btnDone = new Button("Done");
        Button btnCancelPlay = new Button("Cancel");
        
        btnDone.setOnAction(e -> btnDone_Click());
        btnCancelPlay.setOnAction(e ->btnCancelPlaySudoku_Click());
        
        bxPlaySudokuButtons.getChildren().addAll(rgnPlaySudoku, btnDone, btnCancelPlay);
        bxPlaySudokuButtons.setHgrow(rgnPlaySudoku, Priority.ALWAYS);
        
        VBox bxPlayFooter = new VBox(20, txtPlayState, bxPlaySudokuButtons);
        
        BorderPane mainPlaySudokuPane = new BorderPane();
        mainPlaySudokuPane.setPadding(new Insets(10));
        
        mainPlaySudokuPane.setCenter(bxPlaySudokuBoard);
        mainPlaySudokuPane.setBottom(bxPlayFooter);
        
        playSudokuScene = new Scene(mainPlaySudokuPane, 300, 400);
        
        
        /**
         * SOLVE SUDOKU SCENE
         */
        
        
        // ---------- Content section ----------
        
        // ----- Create the sudoku board -----
        
        solveUserInput = new TextField[9][9];
        
        // Initialize the userInput
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                solveUserInput[i][j] = new TextField();
                solveUserInput[i][j].setPrefWidth(30);
            }
        }
        
        HBox[] solveRows = new HBox[9];
        
        // Initialize the rows
        for (int i = 0; i < 9; i++) {
            solveRows[i] = new HBox(5);
        }
        
        // Add the text input fields into the rows
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                solveRows[i].getChildren().add(solveUserInput[i][j]);
            }
        }
        
        VBox bxSolveSudokuBoard = new VBox(5);
        
        // Insert each row into the board
        for (int i = 0; i < 9; i++) {
            bxSolveSudokuBoard.getChildren().add(solveRows[i]);
        }
        
        // ---------- Description section ----------
        
        txtSolveState = new Text();
        
        // ---------- Button section ----------
        
        HBox bxSolveSudokuButtons = new HBox(20);
        
        Region rgnSolveSudoku = new Region();
        
        Button btnSolve = new Button("Solve!");
        Button btnCancelSolve = new Button("Cancel");
        
        btnSolve.setOnAction(e -> btnSolve_Click());
        btnCancelSolve.setOnAction(e ->btnCancelSolve_Click());
        
        bxSolveSudokuButtons.getChildren().addAll(rgnSolveSudoku, btnSolve, btnCancelSolve);
        bxSolveSudokuButtons.setHgrow(rgnSolveSudoku, Priority.ALWAYS);
        
        VBox bxSolveFooter = new VBox(20, txtSolveState, bxSolveSudokuButtons);
        
        BorderPane mainSolveSudokuPane = new BorderPane();
        mainSolveSudokuPane.setPadding(new Insets(10));
        
        mainSolveSudokuPane.setCenter(bxSolveSudokuBoard);
        mainSolveSudokuPane.setBottom(bxSolveFooter);
        
        solveSudokuScene = new Scene(mainSolveSudokuPane, 300, 350);
        
        
        /**
         * ABOUT US SCENE
         */
        
        
        // ---------- Title Section ----------
        
        ImageView auLogoView = new ImageView(icon);
        
        auLogoView.setFitWidth(100);
        auLogoView.setFitHeight(100);
        
        Text auMainTitle = new Text("Sudoku Solver!");
        Text auSubTitle = new Text("...with the game");
        
        auMainTitle.setFont(new Font(40));
        auSubTitle.setFont(new Font(24));
        VBox auTitleCard = new VBox(auMainTitle, auSubTitle);
        
        HBox auTitleSection = new HBox(30, auLogoView, auTitleCard);
        
        // ---------- Content section ----------
        
        Text groupMembers = new Text("Group Members");
        Text groupMember1 = new Text("1. Meklit Ayalew --- ETS 0444/12");
        Text groupMember2 = new Text("2. Milkaai Getachew --- ETS 0478/12");
        Text groupMember3 = new Text("3. Naol Tamrat --- ETS 0512/12");
        Text groupMember4 = new Text("4. Tselote Yonas --- ETS 1052/12");
        Text groupMember5 = new Text("5. Wengelawit Tsegaye --- ETS 1054/12");
        Text groupMember6 = new Text("6. Yohannes Kassa --- ETS 1063/12");
        
        groupMembers.setFont(new Font(20));
        
        VBox bxContribution = new VBox(12, groupMembers, groupMember1, groupMember2
                                        , groupMember3, groupMember4, groupMember5
                                        , groupMember6);
        bxContribution.setPadding(new Insets(20, 0, 0, 0));
        
        Region auRegion = new Region();
        
        Button btnGoBack = new Button("Go Back");
        btnGoBack.setOnAction(e -> auButtonGoBack_Click());
        btnGoBack.setPrefWidth(80);
        
        HBox auButtonSection = new HBox(auRegion, btnGoBack);
        
        auButtonSection.setHgrow(auRegion, Priority.ALWAYS);
        
        BorderPane mainAboutUsPane = new BorderPane();
        mainAboutUsPane.setTop(auTitleSection);
        mainAboutUsPane.setCenter(bxContribution);
        mainAboutUsPane.setBottom(auButtonSection);
        mainAboutUsPane.setPadding(new Insets(30));
        aboutUsScene = new Scene(mainAboutUsPane, stageWidth, stageHeight);

        
        // ---------- Finish setting up the scene ----------
        
        bxTitleSection.setAlignment(Pos.TOP_LEFT);
         
        BorderPane mainPane = new BorderPane();
        mainPane.setTop(bxTitleSection);
        mainPane.setCenter(bxContentSection);
        mainPane.setBottom(bxButtonBox);
        
        mainPane.setPadding(new Insets(20));
        mainMenuScene = new Scene(mainPane, 600, 400, Color.LIGHTGREY);
        
        // ---------- Bring everything together ----------
        
        // Set the starting scene to be the main menu
        primaryStage.setScene(mainMenuScene);
        
        
        // Remove ability to resize window
        primaryStage.setResizable(false);
        
        // Add the icon to the program
        primaryStage.getIcons().add(icon);
        
        // Set the title of the stage
        primaryStage.setTitle("Sudoku Solver + Game");
        
        primaryStage.show();
    }
    
    /**
     * MAIN MENU METHODS
     */
    
    public void btnSelect_Click() {
        
        if (rdoPlayGame.isSelected()) {
            difficultyStage.showAndWait();
        }
        else if (rdoSolveSudoku.isSelected()) {
            stage.setScene(solveSudokuScene);
            stage.setTitle("Solve Sudoku");
        }
        else if (rdoAboutUs.isSelected()) {
            stage.setScene(aboutUsScene);
            stage.setTitle("About us");
        }
        
    }
    
    public void btnClose_Click() {
        stage.close();
    }
    
    public void rdoPlayGame_Click() {
        String description = "";
        
        description += "Play a round of Sudoku with configurable levels\n";
        description += "consisting of varying levels of difficulty.";
        
        txtMenuDescription.setText(description);
    }
    
    public void rdoSolveSudoku_Click() {
        String description = "";
        
        description += "Let the computer do your job! Solve that one hard\n";
        description += "Sudoku game that frustrated you for a long time.";
        
        txtMenuDescription.setText(description);
    }
    
    public void rdoAboutUs_Click() {
        String description = "";
        
        description += "Development team information.";
        
        txtMenuDescription.setText(description);
    }
    
    
    /**
     * DIFFICULTY STAGE METHODS
     */
    
    
    public void btnPlay_Click() {
        stage.setScene(playSudokuScene);
        stage.setTitle("Play Sudoku");
        
        if (rdoEasy.isSelected()) {
            fillPlayBoard(1);
        }
        else if (rdoMedium.isSelected()) {
            fillPlayBoard(36);
        }
        else if (rdoHard.isSelected()) {
            fillPlayBoard(45);
        }
        difficultyStage.close();
    }
    
    public void btnCancelPlay_Click() {
        difficultyStage.close();
    }
    
    public void fillPlayBoard(int missingAmount) {
        
        playNumbers = new Sudoku(9, missingAmount);
        
        playNumbers.fillValues();
        
        int[][] generatedNumbers = playNumbers.getMatrix();
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                
                if (generatedNumbers[i][j] != 0) {
                    playUserInput[i][j].setText(Integer.toString(generatedNumbers[i][j]));
                    playUserInput[i][j].setEditable(false);
                }
            }
        }
    }
    
    
    /**
     * PLAY SUDOKU METHODS
     */
    
    
    public void btnDone_Click() {
        
        txtPlayState.setText("");
        
        int[][] finalResult = playNumbers.getMatrix();
        
        // Check if your solution meets the requirements...
        String playState = "";
        boolean cellEmpty = false;
        boolean invNum = false;
        boolean invChar = false;
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // If a cell is still left empty...
                if (playUserInput[i][j].getText().equals("")) {
                    cellEmpty = true;
                }
                
                // check if the entered characters are valid...
                try {
                    // Remove all the possible whitespace from the input
                    String n = playUserInput[i][j].getText().replaceAll("\\s+","");
                    
                    if (Integer.parseInt(n) < 1
                     || Integer.parseInt(n) > 9) {
                        invNum = true;
                    }
                    finalResult[i][j] = Integer.parseInt(n);
                }
                catch (NumberFormatException ex) {
                    invChar = true;
                }
            }
        }
        
        if (cellEmpty) playState += "Unsolved! Cells still left empty!\n";
        if (invNum) playState += "Unsolved! Invalid numbers are entered!\n";
        if (invChar) playState += "Unsolved! Invalid characters are entered!\n";
        
        if (!playState.equals("")) {
            txtPlayState.setText(playState);
            return;
        }
        
        // check if the numbers are valid
        if (!checkBoard(finalResult)) {
            txtPlayState.setText("Unsolved! There's an error somewhere!");
            return;
        }
        
        // if everything is good ...
        txtPlayState.setText("You won!");
    }
    
    public void btnCancelPlaySudoku_Click() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                playUserInput[i][j].setText("");
                playUserInput[i][j].setEditable(true);
            }
        }
        txtPlayState.setText("");
        stage.setTitle("Sudoku Game + Solver");
        stage.setScene(mainMenuScene);
    }
    
    /**
     * SOLVE SUDOKU METHODS
     */
    
    
    public void btnSolve_Click() {
        
        solveNumbers = new int[9][9];
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                
                // First designate all cells that are empty
                if (solveUserInput[i][j].getText().equals("")) {
                    solveNumbers[i][j] = 0;
                    continue;
                }
                // check if the numbers are valid
                try {
                    if (Integer.parseInt(solveUserInput[i][j].getText()) < 1
                     || Integer.parseInt(solveUserInput[i][j].getText()) > 9) {
                        txtSolveState.setText("Unsolvable!");
                        return;
                    }
                    solveNumbers[i][j] = Integer.parseInt(solveUserInput[i][j].getText());
                }
                catch (NumberFormatException ex) {
                    txtSolveState.setText("Unsolvable!");
                    return;
                }
            }
        }
        
        // check if the numbers are valid
        if (!checkBoard(solveNumbers)) {
            txtSolveState.setText("Unsolvable!");
            return;
        }
        
        // If all the above requirements are satisfied...
        solveSudoku(solveNumbers);
        
        // If the board has an answer/solution...
        if (checkBoard(solveNumbers))
            showResult(solveNumbers);
        else {
            txtSolveState.setText("Unsolvable!");
        }
        
    }
    
    public void solveSudoku(int[][] numbers) {
        
        // Reset the board
        txtSolveState.setText("");
        
        if (numbers == null || numbers.length == 0) {
            txtSolveState.setText("Unsolvable!");
            return;
        }
        
        solve(numbers);
    }
    
    // Check the board for if its valid for an attempt in solving
    public boolean checkBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int n = board[i][j];
                for (int k = 0; k < board.length; k++) {
                    // Check the rows for validity
                    if (board[k][j] != 0 && board[k][j] == n && k != i) {
                        return false;
                    }
                    // Check the columns for validity
                    if (board[i][k] != 0 && board[i][k] == n && k != j) {
                        return false;
                    }
                    // Check the enclosing 3 x 3 block
                    if (board[3 * (i / 3) + k / 3][3 * (j / 3) + k % 3] != 0 &&
                        board[3 * (i / 3) + k / 3][3 * (j / 3) + k % 3] == n &&
                        (3 * (i / 3) + k / 3) != i && (3 * (j / 3) + k % 3) != j) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public boolean solve(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
		if (board[i][j] == 0) {
                    // trial. Try 1 through 9
                    for (int n = 1; n <= 9; n++) {
			if (isValid(board, i, j, n)) {
                            board[i][j] = n;
				if (solve(board)) {
                                    // if its the solution...
                                    return true;
				} 
                                else {
                                    // otherwise go back
                                    board[i][j] = 0;
				}
			}
                    }
                    return false;
		}
            }
	}
	return true;
    }
    
    public static boolean isValid(int[][] board, int row, int col, int n) {
	for (int i = 0; i < board.length; i++) {
            // Check the rows for validity
            if (board[i][col] != 0 && board[i][col] == n) {
		return false;
            }
            // Check the columns for validity
            if (board[row][i] != 0 && board[row][i] == n) {
		return false;
            }
            // Check the enclosing 3 x 3 block
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] != 0 &&
		board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == n) {
		return false;
            }
	}

	// if it's valid...
	return true;
    }
    
    // Prints out the array into the Sudoku board
    public void showResult(int[][] numbers) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                solveUserInput[i][j].setText(Integer.toString(numbers[i][j]));
            }
        }
    }
    
    // Return to main menu
    public void btnCancelSolve_Click() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                solveUserInput[i][j].setText("");
            }
        }
        txtSolveState.setText("");
        stage.setTitle("Sudoku Game + Solver");
        stage.setScene(mainMenuScene);
    }
    
    
    /**
     * ABOUT US METHODS
     */
    
    
    // Return to main menu
    public void auButtonGoBack_Click() {
        stage.setScene(mainMenuScene);
        stage.setTitle("Sudoku Game + Solver");
    }
}