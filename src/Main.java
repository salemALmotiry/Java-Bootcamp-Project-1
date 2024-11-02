import java.util.*;

public class Main {

    public static int [] prints ( ) throws InputMismatchException {
        Scanner input = new Scanner(System.in);
        System.out.println("Please wait game initialization...");
        System.out.println("--------------------------");
        System.out.println("Game settings.");
        System.out.println("1 to play one round\n2 to play three rounds");

        int type = input.nextInt();
        if (type !=1 && type !=2)
            throw new InputMismatchException();
        System.out.println("Game type");
        System.out.println("1 to play original tic tac toc\n" +
                "2 to play INFINITE TIC-TAC-TOE! (new)");
        int game = input.nextInt();
        if ( game !=1 && game !=2)
            throw new InputMismatchException();

        System.out.println("Game mode.");
        System.out.println("""
                1 to play two players
                2 to play against easy ai
                3 to play against hard ai.""");
        int mode = input.nextInt();

        if(mode <1 || mode >3 )
            throw new InputMismatchException();



        return new int[]{type,game,mode};
    }
    public static void main(String[] args) {

    try {


        String [] [] board= init_board(3,3);

        int [] reuslt = prints();
        int type = reuslt[0];
        int game = reuslt[1];
        int mode = reuslt[2];
        String winner;
        switch (type) {

            case 1:
                if (mode == 1)
//                 array passed by reference.
                    winner = player_vs_player(board,game);
                else if (mode == 2)
                    winner =  player_vs_computer_easy_mode(board,game);
                else
                    winner =  player_vs_ai_hard_mode(board,game);

                System.out.println(winner.equals("Tie")?"\nTie":"\n"+winner+" won the game");
                break;
            case 2:

                int x = 0;
                int o = 0;

                for (int i = 0; i < 3; i++) {
                    System.out.println("\n+++++++++++++++++ Round "+(i+1)+" +++++++++++++++++");
                    board = init_board(3,3); // reset the board
                    if (mode == 1)
                        winner = player_vs_player(board,game);
                    else if (mode == 2)
                        winner = player_vs_computer_easy_mode(board,game);
                    else
                       winner =  player_vs_ai_hard_mode(board,game);

                    if(winner.equals("X"))
                        x++;
                    else if (winner.equals("O"))
                        o++;

                }

                if (x>o)
                    System.out.println("\nX won the game");
                else if(o>x)
                    System.out.println("\nO won the game");
                else
                    System.out.println("\nTie");






        }

    } catch (InputMismatchException e) {
        System.out.println("Invalid input.");
    }

    }


    // Essential Game Functions

    /**
     * Sets up the board for the beginning of a new game.
     */
    public static String [][] init_board (int x,int y) {
        String[][] board = new String[x][y];
        int jump = 3 ;
        int counter = 1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {


                board[i][j] = String.valueOf(counter);
                counter++;
                if (jump ==j)
                    break;
            }


        }
        return board;
    }


    /**
     * Updates the game board based on game state changes.
     */

    public static void update_board (String [][] board ) {

        System.out.println("___________________");
        for (String[] strings : board) {

            for (String string : strings) {

                System.out.print("|  " + string + "  ");
                if (string.equals("3") || string.equals("6") || string.equals("9")) {
                    System.out.print("|");
                }
            }

            System.out.print("\n|_________________|\n");

        }
    }


    /**
     * Sets a specific position on the game board.
     */

    public static void set (String [][] board , String played, int x , int y ) {
        board[x][y] = played;

    }




    // Game State and Validation Functions


    /**
     * Toggles the current player to let them take their turn, then advances
     * to the next player for the following call. Alternates between players
     * (e.g., player 1 and player 2) every time it’s invoked.
     *
     * @return Integer representing the new current player
     */
    public static int next_turn(String [] [] board,int played,int player,Queue<Integer> players_moves) {
        int [] position = get_positions(played);
        boolean playable = check_playable(board, position[0], position[1]);
        if (playable ) {
            set(board, player == 1 ?"X":"O", position[0], position[1]);
            players_moves.add(played);
            return player == 1 ? 2 : 1;
        } else {

            System.out.println("position taken try another one");
            return player == 1 ? 1 : 2;


        }

    }


    /**
     * Checks if a move is playable in the current game state.
     * Verifies whether a move is allowed based on current board status.
     *
     * @return Boolean indicating if the move is valid
     */

    public static boolean check_playable(String [] [] board,int x,int y ) {

        return !board[x][y].equals("X") && !board[x][y].equals("O");

    }



    /**
     * Checks for a winning condition on the board.
     * Analyzes current board state to determine if a player has won.
     *
     * @return String indicating if an "X" or "O" is winner, or "null" otherwise
     */
    public static String check_winner (String [][] board ) {
        //   0 1 2
        // 0 0 1 2
        // 1 3 4 5
        // 2 6 7 8

        // 00 01 02
        // x  x  x
        //
        //
        //
        if(board[0][0].equals(board[0][1]) && board[0][1].equals(board[0][2])) {
            return board[0][0];
            // 00 11 22
            //x
            //  x
            //    x
        }else if(board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            return board[0][0];
            // 00 10 20
            // x
            // x
            // x
        }else if(board[0][0].equals(board[1][0]) && board[0][0].equals(board[2][0])) {
            return board[0][0];
            // 10 11 21
            //
            // x   x  x
            //
        }else if(board[1][0].equals(board[1][1]) && board[1][0].equals(board[1][2])) {
            return board[1][0];
            // 02 12 22
            //
            //
            // x   x   x
        }else if(board[0][2].equals(board[1][2]) && board[0][2].equals(board[2][2])) {

            return board[0][2];
            // 20 21 22
            //        x
            //        x
            //        x
        }else if(board[2][0].equals(board[2][1]) && board[2][0].equals(board[2][2])) {
            return board[2][0];
            // 20 21 22
            //     x
            //     x
            //     x
        }else if(board[0][1].equals(board[1][1]) && board[0][1].equals(board[2][1])) {
            return board[0][1];
        }else if(board[2][0].equals(board[1][1]) && board[2][0].equals(board[0][2])) {
            return board[2][0];
        }

        return "null";
    }


    /**
     * Converts a player's input position (1-9) into 2D board coordinates.
     * The input corresponds to the following positions:
     * 1 | 2 | 3
     * ---------
     * 4 | 5 | 6
     * ---------
     * 7 | 8 | 9
     *
     * @param position The player's chosen position (1-9)
     * @return int[] containing the row and column indices [row, col]
     */
    public static int [] get_positions (int position) {


        int row = (position - 1) / 3;
        int col = (position - 1) % 3;
        return new int[] {row,col};

    }


    /**
     * Identifies open spots on the game board.
     */
    public static boolean open_spots (String [][] board ) {

        int jump = 3 ;
        int counter = 1;
        for (String[] strings : board) {
            for (int j = 0; j < strings.length; j++) {
                if (strings[j].equals(String.valueOf(counter)))
                    return false;
                counter++;
                if (jump == j)
                    break;
            }
        }


        return true;}


    // AI and Decision-Making Functions

    /**
     * Determines the best move for the AI or player using the Minimax algorithm.
     * This function analyzes possible moves and returns the optimal choice.
     */

    public static int best_move (String [] [] board ) {
        int best_score = Integer.MIN_VALUE;
        int move = 0;
        String move_str;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if(!board[i][j].equals("X") && !board[i][j].equals("O")){

                    move_str = board[i][j];
                    board[i][j] = "O";
                    int score = minimax(board,0,false);
                    board[i][j] = move_str;

                    if(score > best_score){
                        best_score = score;
                        move = Integer.parseInt(move_str);

                    }
                }

        }}

        return move;
    }

    /**
     * Implements the Minimax algorithm for decision-making in a game.
     * Recursively evaluates moves to maximize or minimize the outcome depending on the player's turn.
     * Returns the score of the optimal move for the given state of the game.
     *
     * @param depth Current depth of recursion
     * @param is_maximizing Boolean flag indicating whether to maximize(AI) or minimize(human) score
     * @return Integer score of the best possible move
     */

    public static int minimax (String [][] board ,int depth,boolean is_maximizing) {

        String result = check_winner(board);
        if(!result.equals("null")) {

            if(result.equals("X")){
                return -1;
            }
            else {

                return 1;
            }
        }
        if  (open_spots(board)) {
            return 0;
        }


        int best_score;
        if(is_maximizing) {
            best_score = Integer.MIN_VALUE;

           for (int i = 0; i < board.length; i++) {
               for (int j = 0; j < board[i].length; j++) {
                   if(!board[i][j].equals("X") && !board[i][j].equals("O")){
                       String move_str = board[i][j];
                       board[i][j] = "O";
                       int score = minimax(board,depth+1,false);
                       board[i][j] = move_str;
                       best_score = Math.max(score,best_score);

                   }

               }}

        }else {
            best_score = Integer.MAX_VALUE;
           for (int i = 0; i < board.length; i++) {
               for (int j = 0; j < board[i].length; j++) {
                   if(!board[i][j].equals("X") && !board[i][j].equals("O")){
                       String move_str = board[i][j];
                       board[i][j] = "X";
                       int score = minimax(board,depth+1,true);
                       board[i][j] = move_str;


                       best_score = Math.min(score,best_score);

                   }

               }}
        }
        return best_score ;


    }


    // Game Modes and Player Interactions


    /**
     * Manages gameplay between two human players.
     * Facilitates turn-taking and interaction for player vs. player mode.
     */

    public static String player_vs_player(String [] [] board, int game) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();


        update_board(board);

        Queue<Integer> players_moves = new LinkedList<>();
        int player = rand.nextInt(1,2);

        while (true) {

            int played;
            if (game == 2 && players_moves.size() == 5) {
                played = players_moves.remove();
                int [] position = get_positions(played);
                set(board,String.valueOf(played),position[0],position[1]);
            }
            System.out.print("\nPlayer "+player+" please enter your play: ");
            played = input.nextInt();

            if (played >9 || played <0){
                System.out.println("Player "+player+" game range is 1 to 9");
                continue;
            }
            player = next_turn(board,played,player,players_moves);
            update_board(board);

            String win = check_winner(board);
            if (!win.equals("null")) {
                return win;
            }
            if(open_spots(board)) {
                return "Tie";
            }


        }


    }

    /**
     * Manages gameplay between a human player and a computer opponent using random moves
     */

    public static String player_vs_computer_easy_mode(String [] [] board, int game) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();


        update_board(board);
        int player = rand.nextInt(1,2);

        Queue<Integer> players_moves = new LinkedList<>();
        while (true) {

            int played;

            if (game == 2 && players_moves.size() == 5) {
                played = players_moves.remove();
                int [] position = get_positions(played);
                set(board,String.valueOf(played),position[0],position[1]);
            }

            if(player == 1){
                System.out.print("\nPlayer "+player+" please enter your play: ");
                played = input.nextInt();
                if (played >9 || played <0){
                    System.out.println("\nPlayer "+player+" game range is 1 to 9");
                    continue;
                }

            }else {
                System.out.println("\nAI turn\n");
                played = rand.nextInt(1,9);

            }

            player = next_turn(board,played,player,players_moves);
            update_board(board);

            String win = check_winner(board);
            if (!win.equals("null")) {
                return win;
            }
            if(open_spots(board)) {
                return "Tie";
            }


        }


    }


    /**
     * Manages gameplay between a human player and an AI opponent using strategic decision-making.
     * Provides a more challenging experience for the player in hard mode.
     */
    public static String player_vs_ai_hard_mode(String [] [] board, int game) {
        Scanner input = new Scanner(System.in);
        update_board(board);
        int player = 2;

        Queue<Integer> players_moves = new LinkedList<>();

        while (true) {

            int played;
            if (game == 2 && players_moves.size() == 5) {
                played = players_moves.remove();
                int [] position = get_positions(played);
                set(board,String.valueOf(played),position[0],position[1]);
            }
            if(player == 1){
                System.out.print("\nPlayer "+player+" please enter your play: ");

                played = input.nextInt();
                if (played >9 || played <0){
                    System.out.println("Player "+player+" game range is 1 to 9");
                    continue;
                }

            }else {
                System.out.println("\nAI turn");
                played = best_move(board);

            }

            player = next_turn(board,played,player,players_moves);
            update_board(board);


            String win = check_winner(board);
            if (!win.equals("null")) {
                return win;
            }
            if(open_spots(board)) {
               return "Tie";
            }


        }


    }


}
