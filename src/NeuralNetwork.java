import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class NeuralNetwork {

    static Random random;
    static double Iteration = 0;

    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        random = new Random();
        double lr = 0.1;

        //HERE WE ARE CREATING THE INPUT LAYER 
        //FOR THIS PARTICULAR EXAMPLE THERE ARE 3 INPUT NUERONS
        InputLayer inputLayer = new InputLayer(3);
        //NOW WE ARE MAKING THE NEURON OF INPUT LAYER
        //HAVING BIAS 0 FOR INPUT LAYER
        inputLayer.inputNuerons[0] = new Neuron("A", 0, 3);			//NAME "A" and BIAS 0 for input layer, and there will be 3 connections from "A" to other neurons
        inputLayer.inputNuerons[1] = new Neuron("B", 0, 3);			//..soon
        inputLayer.inputNuerons[2] = new Neuron("C", 0, 3);

        //HERE WE ARE MAKING TWO HIDDEN LAYERS
        //YOU CAN MAKE ONE OR THREE OR ELSE LAYERS
        //I AM MAKING 3 NEURONS IN FIRST HIDDEN LAYER
        //AND 2 NEURONS IN SECOND HIDDEN LAYER
        HiddenLayer firstHiddenLayer = new HiddenLayer(3);
        HiddenLayer secondHiddenLayer = new HiddenLayer(2);
        //NOW WE MUST MAKE 5 NEURONS 3 OF FIRST AND 2 OF SECOND HIDDEN LAYER
        firstHiddenLayer.hiddenNuerons[0] = new Neuron("D", random.nextDouble(), 2);	//NAME D and random bias, 2 connections for each neuron of firsthiddenlayer
        firstHiddenLayer.hiddenNuerons[1] = new Neuron("E", random.nextDouble(), 2);
        firstHiddenLayer.hiddenNuerons[2] = new Neuron("F", random.nextDouble(), 2);
        secondHiddenLayer.hiddenNuerons[0] = new Neuron("G", random.nextDouble(), 1);		//know there is only one connection from second layer to output layer
        secondHiddenLayer.hiddenNuerons[1] = new Neuron("H", random.nextDouble(), 1);

        //NEW LINES ADDED FOR SIMPLICITY:
        Neuron A = inputLayer.inputNuerons[0];
        Neuron B = inputLayer.inputNuerons[1];
        Neuron C = inputLayer.inputNuerons[2];
        Neuron D = firstHiddenLayer.hiddenNuerons[0];
        Neuron E = firstHiddenLayer.hiddenNuerons[1];
        Neuron F = firstHiddenLayer.hiddenNuerons[2];
        Neuron G = secondHiddenLayer.hiddenNuerons[0];
        Neuron H = secondHiddenLayer.hiddenNuerons[1];

        //NOW FINALLY CREATING THE OUTPUT LAYER
        //AND THERE IS ONE NEURON IN THE OUTPUT LAYER
        OutputLayer outputLayer = new OutputLayer(1);
        //MAKING THE NEURON
        outputLayer.outputNuerons[0] = new Neuron("I", random.nextDouble(), 0);			//no connection from output layer
        Neuron I = outputLayer.outputNuerons[0];
        //NOW FIRST WE MUST MAKE THE CONNECTION BETWEEN EACH NEURONS
        //FIRST WE MUST CONNECT NEURON "A" WITH "D","E","F"
        Connection con_A_D = new Connection( //A-D
                A, //A
                D, //D
                random.nextDouble() //random weight
        );
        Connection con_A_E = new Connection( //A-E
                A, //A
                E, //E
                random.nextDouble()
        );
        Connection con_A_F = new Connection( //A-F
                A, //A
                F, //F
                random.nextDouble()
        );
        //NOW EACH CONNECTION KNOW THEY ARE CONNECTED TO "A" and "D","E","F" RESPECTIVELY
        //BUT "A" DOESNT KNOW TO WHOM CONNECTINS IT IS CONNECTED
        //AND A NEURON MUST KNOW ALL THE OUTGOING CONNECTIONS FROM IT SO WE MUST TELL "A" ABOUT THE OUTGOING CONNECTIONS
        A.connections[0] = con_A_D;
        A.connections[1] = con_A_E;
        A.connections[2] = con_A_F;

        //NOW WE MUST CONNECT NEURON "B" WITH "D","E","F"
        Connection con_B_D = new Connection( //B-D
                B, //B
                D, //D
                random.nextDouble() //random weight
        );
        Connection con_B_E = new Connection( //B-E
                B, //B
                E, //E
                random.nextDouble()
        );
        Connection con_B_F = new Connection( //B-F
                B, //B
                F, //F
                random.nextDouble()
        );
        //NOW EACH CONNECTION KNOW THEY ARE CONNECTED TO "B" and "D","E","F" RESPECTIVELY
        //BUT "B" DOESNT KNOW TO WHOM CONNECTINS IT IS CONNECTED
        //AND A NEURON MUST KNOW ALL THE OUTGOING CONNECTIONS FROM IT SO WE MUST TELL "B" ABOUT THE OUTGOING CONNECTIONS
        B.connections[0] = con_B_D;
        B.connections[1] = con_B_E;
        B.connections[2] = con_B_F;

        //FINALLY WE MUST CONNECT NEURON "C" WITH "D","E","F"
        Connection con_C_D = new Connection( //C-D
                C, //C
                D, //D
                random.nextDouble() //random weight
        );
        Connection con_C_E = new Connection( //C-E
                C, //C
                E, //E
                random.nextDouble()
        );
        Connection con_C_F = new Connection( //C-F
                C, //C
                F, //F
                random.nextDouble()
        );
        //NOW EACH CONNECTION KNOW THEY ARE CONNECTED TO "C" and "D","E","F" RESPECTIVELY
        //BUT "C" DOESNT KNOW TO WHOM CONNECTINS IT IS CONNECTED
        //AND A NEURON MUST KNOW ALL THE OUTGOING CONNECTIONS FROM IT SO WE MUST TELL "C" ABOUT THE OUTGOING CONNECTIONS
        C.connections[0] = con_C_D;
        C.connections[1] = con_C_E;
        C.connections[2] = con_C_F;

        System.out.println("PASSED ALL, NEW WORK WILL BE DONE SOON!!!!");

        //THE FIRST LAYER IS CONNECTED WITH HIDDEN LAYER ONE KNOW HIDDEN LAYER ONE WILL CONNECT WITH HIDDEN LAYER TWO
        //WE MUST CONNECT NEURON "D" WITH "G","H"
        Connection con_D_G = new Connection( //D-G
                D, //D
                G, //G
                random.nextDouble() //random weight
        );
        Connection con_D_H = new Connection( //D-H
                D, //D
                H, //H
                random.nextDouble()
        );

        //NOW EACH CONNECTION KNOW THEY ARE CONNECTED TO "D" and "G","H" RESPECTIVELY
        //BUT "D" DOESNT KNOW TO WHOM CONNECTINS IT IS CONNECTED
        //AND A NEURON MUST KNOW ALL THE OUTGOING CONNECTIONS FROM IT SO WE MUST TELL "D" ABOUT THE OUTGOING CONNECTIONS
        D.connections[0] = con_D_G;
        D.connections[1] = con_D_H;

        //WE MUST CONNECT NEURON "E" WITH "G","H"
        Connection con_E_G = new Connection( //E-G
                E, //E
                G, //G
                random.nextDouble() //random weight
        );
        Connection con_E_H = new Connection( //E-H
                E, //E
                H, //H
                random.nextDouble()
        );

        //NOW EACH CONNECTION KNOW THEY ARE CONNECTED TO "F" and "G","H" RESPECTIVELY
        //BUT "E" DOESNT KNOW TO WHOM CONNECTINS IT IS CONNECTED
        //AND A NEURON MUST KNOW ALL THE OUTGOING CONNECTIONS FROM IT SO WE MUST TELL "D" ABOUT THE OUTGOING CONNECTIONS
        E.connections[0] = con_E_G;
        E.connections[1] = con_E_H;

        //WE MUST CONNECT NEURON "F" WITH "G","H"
        Connection con_F_G = new Connection( //F-G
                F, //F
                G, //G
                random.nextDouble() //random weight
        );
        Connection con_F_H = new Connection( //F-H
                F, //E
                H, //H
                random.nextDouble()
        );

        //NOW EACH CONNECTION KNOW THEY ARE CONNECTED TO "F" and "G","H" RESPECTIVELY
        //BUT "F" DOESNT KNOW TO WHOM CONNECTINS IT IS CONNECTED
        //AND A NEURON MUST KNOW ALL THE OUTGOING CONNECTIONS FROM IT SO WE MUST TELL "D" ABOUT THE OUTGOING CONNECTIONS
        F.connections[0] = con_F_G;
        F.connections[1] = con_F_H;

        //NOW CONNECTING G AND H TO I
        //CONNECTING G TO I
        Connection con_G_I = new Connection( //G-I
                G, //G
                I, //I
                random.nextDouble() //random weight
        );
        //AND TRLLING G ABOUT THE CONNECTION
        G.connections[0] = con_G_I;

        //CONNECTING H TO I
        Connection con_H_I = new Connection( //H-I
                H, //H
                I, //I
                random.nextDouble() //random weight
        );
        //AND TRLLING H ABOUT THE CONNECTION
        H.connections[0] = con_H_I;

        System.out.println("HURRAY THE NEURAL NETWORK IS FORMED");

        //NOW WE MUST READ THE DATA SET
        MyCSV myCSV = new MyCSV("/home/aftab/NN-DATA.csv");     //THE DATA SET FILE "NN_DATA.csv IS IN "E" RIVE
        double dataSet[][] = myCSV.readCSV();           //MY OWN CLASS TO READ A CSV FILE
        int rows = dataSet.length;
        int cols = dataSet[0].length;

        double totalError;
        do {
            //System.out.println("--------------#######~~~~~~~~~@@@@@@@@@@@~~~~~~~~~#######-------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            //System.out.println("WE ARE STARTING THE FEED FORWARD AND BACK PROPAGATION:");
            totalError = 0.0d;
            int rnd = random.nextInt(rows);                //rendom no of record to start with i.e any one from 704 record
            Boolean pass = true;                           //this will allow to pass the condition for one time
            for (int i = rnd; (i % rows) != rnd || pass; i++) //starting to begin with random record; i must not be that random:now note that if our i must not be that random the loop will never work hence we pass the condition by pass variable and the pass become false, now when we do i++ and i will again become that random the loop will break, hence we iterate through all the records(refer the output at console the record # first record and so on..)
            {
                //System.out.print("FOR RECORD # " + (i % rows) + " ");
                pass = false;
                double cutError = 0;

                double itr = 0;
                while (true) {
                    //READING THE ROW AND FEEDING THE VALUES IN INPUT LAYER
                    //NOTE THAT THE VALUES ARE INPUT FOR THE INPUT LAYER
                    //BUT INPUT LAYER WILL GENERATE THE SAME OUTPUT WITHOUT DOING ANY THING WITH THE INPUT VALUES
                    //SO WE MAKE THEN DIRECTLY THE OUTPUT 
                    A.output = dataSet[i % rows][0];     //INPUT OF "A" or OUTPUT OF "A"
                    B.output = dataSet[i % rows][1];     //INPUT OF "B" or OUTPUT OF "B"
                    C.output = dataSet[i % rows][2];     //INPUT OF "C" or OUTPUT OF "C"

                    //NOW WE MUST SEND INPUT TO THE firstHiddenLayer
                    //INPUT TO "D"
                    D.input = con_A_D.inputNeuron.output * con_A_D.currentWeight //   "A" * A-D
                            + con_B_D.inputNeuron.output * con_B_D.currentWeight // + "B" * B-D
                            + con_C_D.inputNeuron.output * con_C_D.currentWeight;  // + "C" * C-D
                    D.calcOutput();                                                // D got its input must calulate its output
                    //System.out.println("output of D = "+D.output);

                    //INPUT TO "E"
                    E.input = con_A_E.inputNeuron.output * con_A_E.currentWeight //   "A" * A-E
                            + con_B_E.inputNeuron.output * con_B_E.currentWeight // + "B" * B-E
                            + con_C_E.inputNeuron.output * con_C_E.currentWeight;  // + "C" * C-E
                    E.calcOutput();                                                // E got its input must calulate its output

                    //INPUT TO "F"
                    F.input = con_A_F.inputNeuron.output * con_A_F.currentWeight //   "A" * A-F
                            + con_B_F.inputNeuron.output * con_B_F.currentWeight // + "B" * B-F
                            + con_C_F.inputNeuron.output * con_C_F.currentWeight;  // + "C" * C-F
                    F.calcOutput();                                                // F got its input must calulate its output

                    //NOW WE MUST SEND INPUT TO THE secondHiddenLayer
                    //INPUT TO "G"
                    G.input = con_D_G.inputNeuron.output * con_D_G.currentWeight //   "D" * D-G
                            + con_E_G.inputNeuron.output * con_E_G.currentWeight // + "E" * E-G
                            + con_F_G.inputNeuron.output * con_F_G.currentWeight;  // + "F" * F-G
                    G.calcOutput();                                                // G got its input must calulate its output

                    //INPUT TO "H"
                    H.input = con_D_H.inputNeuron.output * con_D_H.currentWeight //   "D" * D-H
                            + con_E_H.inputNeuron.output * con_E_H.currentWeight // + "E" * E-H
                            + con_F_H.inputNeuron.output * con_F_H.currentWeight;  // + "F" * F-H
                    H.calcOutput();                                                // H got its input must calulate its output

                    //FINALLY WE SEND INPUT TO THE LAST LAYER
                    I.input = con_G_I.inputNeuron.output * con_G_I.currentWeight //   "G" * G-I
                            + con_H_I.inputNeuron.output * con_H_I.currentWeight;  // + "H" * H-I
                    I.calcOutput();                                           //I GOT INPUT MUST calculate its out put i.e the output of the entire Nueral Network

                    double target = dataSet[(i % rows)][cols - 1];                             //Target Value
                    double error = (target - I.output) * I.output * (1.0 - I.output);                          //THIS ERROR WILL BE USED IN WEIGHT UPDATIOn
                    //System.out.println("record # " + (i % rows) + "\t:\tcalculated = " + I.output + " target = " + target + " error = " + error);
                    cutError = 1 / 2.0 * Math.pow(target - I.output, 2);
                    //System.out.println("cutError = " + cutError);
                    if (cutError < 0.001) {
                        //System.out.println("Itr " + itr + " error = " + error);
                        totalError = totalError + cutError;
                        //System.out.println("totalError becomes - > "+totalError);

                        break;
                    }
                    itr++;
                    //ELSE BACK PROPAGATION

                    //ERROR OF I i.e ERROR OF OUTPUT LAYER
                    I.error = error;
                    I.bias = I.bias + I.error * lr;
                    //UPDATING WEIGHTS OF 3 CONNECTION LAYER
                    con_G_I.newWieght = con_G_I.currentWeight + (lr * con_G_I.outputNeuron.error * con_G_I.inputNeuron.output);
                    con_H_I.newWieght = con_H_I.currentWeight + (lr * con_H_I.outputNeuron.error * con_H_I.inputNeuron.output);

                    //UPDTING WEIGHT OF 2 CONNECTION LAYER
                    //WE MUST CALCULATE ERROR OF G AND H FIRST TO UPDATE WEIGHTS OF con_D_G,E_G,F_G and con_D_H,E_H,F_H respectively
                    //First find the error of "G"
                    double ed_G = (G.connections[0].currentWeight * G.connections[0].outputNeuron.error);
                    G.error = G.output * (1 - G.output) * ed_G;
                    G.bias = G.bias + G.error * lr;                 //UPDATING BIAS
                    //Now find the error of "H"
                    double ed_H = (H.connections[0].currentWeight * H.connections[0].outputNeuron.error);
                    H.error = H.output * (1 - H.output) * ed_H;
                    H.bias = H.bias + H.error * lr;
                    //SINCE G and H know their weights so we can now calculate the new weights of connections connected to them                                            //con_D_G.outputNeuron --> G
                    con_D_G.newWieght = con_D_G.currentWeight + (lr * con_D_G.outputNeuron.error * con_D_G.inputNeuron.output);
                    con_E_G.newWieght = con_E_G.currentWeight + (lr * con_E_G.outputNeuron.error * con_E_G.inputNeuron.output);
                    con_F_G.newWieght = con_F_G.currentWeight + (lr * con_F_G.outputNeuron.error * con_F_G.inputNeuron.output);
                    con_D_H.newWieght = con_D_H.currentWeight + (lr * con_D_H.outputNeuron.error * con_D_H.inputNeuron.output);
                    con_E_H.newWieght = con_E_H.currentWeight + (lr * con_E_H.outputNeuron.error * con_E_H.inputNeuron.output);
                    con_F_H.newWieght = con_F_H.currentWeight + (lr * con_F_H.outputNeuron.error * con_F_H.inputNeuron.output);

                    //SIMILARLY CALCULATE ERROR OF D,E,F to UPDATE WEIGHTS of con_A_D,B_D,C_D and con_A_E,B_E,C_E and con_C_D,C_E,C_F respectively
                    double ed_D = (D.connections[0].currentWeight * D.connections[0].outputNeuron.error) + (D.connections[1].currentWeight * D.connections[0].outputNeuron.error);
                    D.error = D.output * (1 - D.output) * ed_D;
                    D.bias = D.bias + D.error * lr;
                    double ed_E = (E.connections[0].currentWeight * E.connections[0].outputNeuron.error) + (E.connections[1].currentWeight * E.connections[0].outputNeuron.error);
                    E.error = E.output * (1 - E.output) * ed_E;
                    E.bias = E.bias + E.error * lr;
                    double ed_F = (F.connections[0].currentWeight * F.connections[0].outputNeuron.error) + (F.connections[1].currentWeight * F.connections[0].outputNeuron.error);
                    F.error = F.output * (1 - F.output) * ed_F;
                    F.bias = F.bias + F.error * lr;
                    //SINCE D,E and F know their weights so we can now calculate the new weights of connections connected to them                                            //con_D_G.outputNeuron --> G
                    con_A_D.newWieght = con_A_D.currentWeight + (lr * con_A_D.outputNeuron.error * con_A_D.inputNeuron.output);
                    con_A_E.newWieght = con_A_E.currentWeight + (lr * con_A_E.outputNeuron.error * con_A_E.inputNeuron.output);
                    con_A_F.newWieght = con_A_F.currentWeight + (lr * con_A_F.outputNeuron.error * con_A_F.inputNeuron.output);
                    con_B_D.newWieght = con_B_D.currentWeight + (lr * con_B_D.outputNeuron.error * con_B_D.inputNeuron.output);
                    con_B_E.newWieght = con_B_E.currentWeight + (lr * con_B_E.outputNeuron.error * con_B_E.inputNeuron.output);
                    con_B_F.newWieght = con_B_F.currentWeight + (lr * con_B_F.outputNeuron.error * con_B_F.inputNeuron.output);
                    con_C_D.newWieght = con_C_D.currentWeight + (lr * con_C_D.outputNeuron.error * con_C_D.inputNeuron.output);
                    con_C_E.newWieght = con_C_E.currentWeight + (lr * con_C_E.outputNeuron.error * con_C_E.inputNeuron.output);
                    con_C_F.newWieght = con_C_F.currentWeight + (lr * con_C_F.outputNeuron.error * con_C_F.inputNeuron.output);

                    //**NEW LINES**//
                    //NOW NEW WEIGHTS MUST BECOME CURRENT WEIGHTS
                    con_A_D.currentWeight = con_A_D.newWieght;
                    con_A_E.currentWeight = con_A_E.newWieght;
                    con_A_F.currentWeight = con_A_F.newWieght;
                    con_B_D.currentWeight = con_B_D.newWieght;
                    con_B_E.currentWeight = con_B_E.newWieght;
                    con_B_F.currentWeight = con_B_F.newWieght;
                    con_C_D.currentWeight = con_C_D.newWieght;
                    con_C_E.currentWeight = con_C_E.newWieght;
                    con_C_F.currentWeight = con_C_F.newWieght;

                    con_D_G.currentWeight = con_D_G.newWieght;
                    con_E_G.currentWeight = con_E_G.newWieght;
                    con_F_G.currentWeight = con_F_G.newWieght;
                    con_D_H.currentWeight = con_D_H.newWieght;
                    con_E_H.currentWeight = con_E_H.newWieght;
                    con_F_H.currentWeight = con_F_H.newWieght;

                    con_G_I.currentWeight = con_G_I.newWieght;
                    con_H_I.currentWeight = con_H_I.newWieght;

                }
            }
            //System.out.println("-----------------------------------------------------------");
            //System.out.println("The Total Error for this Iteration is " + totalError);
            //System.out.println("------------------------------------------------------------");

            //NOW PREDICTING THE RECORDS
            double dataset[][] = myCSV.test;
            totalError = 0;
            for (int i = 0; i < dataset.length; i++) {
                A.output = dataSet[i % rows][0];     //INPUT OF "A" or OUTPUT OF "A"
                B.output = dataSet[i % rows][1];     //INPUT OF "B" or OUTPUT OF "B"
                C.output = dataSet[i % rows][2];     //INPUT OF "C" or OUTPUT OF "C"

                //NOW WE MUST SEND INPUT TO THE firstHiddenLayer
                //INPUT TO "D"
                D.input = con_A_D.inputNeuron.output * con_A_D.currentWeight //   "A" * A-D
                        + con_B_D.inputNeuron.output * con_B_D.currentWeight // + "B" * B-D
                        + con_C_D.inputNeuron.output * con_C_D.currentWeight;  // + "C" * C-D
                D.calcOutput();                                                // D got its input must calulate its output
                //System.out.println("output of D = "+D.output);

                //INPUT TO "E"
                E.input = con_A_E.inputNeuron.output * con_A_E.currentWeight //   "A" * A-E
                        + con_B_E.inputNeuron.output * con_B_E.currentWeight // + "B" * B-E
                        + con_C_E.inputNeuron.output * con_C_E.currentWeight;  // + "C" * C-E
                E.calcOutput();                                                // E got its input must calulate its output

                //INPUT TO "F"
                F.input = con_A_F.inputNeuron.output * con_A_F.currentWeight //   "A" * A-F
                        + con_B_F.inputNeuron.output * con_B_F.currentWeight // + "B" * B-F
                        + con_C_F.inputNeuron.output * con_C_F.currentWeight;  // + "C" * C-F
                F.calcOutput();                                                // F got its input must calulate its output

                //NOW WE MUST SEND INPUT TO THE secondHiddenLayer
                //INPUT TO "G"
                G.input = con_D_G.inputNeuron.output * con_D_G.currentWeight //   "D" * D-G
                        + con_E_G.inputNeuron.output * con_E_G.currentWeight // + "E" * E-G
                        + con_F_G.inputNeuron.output * con_F_G.currentWeight;  // + "F" * F-G
                G.calcOutput();                                                // G got its input must calulate its output

                //INPUT TO "H"
                H.input = con_D_H.inputNeuron.output * con_D_H.currentWeight //   "D" * D-H
                        + con_E_H.inputNeuron.output * con_E_H.currentWeight // + "E" * E-H
                        + con_F_H.inputNeuron.output * con_F_H.currentWeight;  // + "F" * F-H
                H.calcOutput();                                                // H got its input must calulate its output

                //FINALLY WE SEND INPUT TO THE LAST LAYER
                I.input = con_G_I.inputNeuron.output * con_G_I.currentWeight //   "G" * G-I
                        + con_H_I.inputNeuron.output * con_H_I.currentWeight;  // + "H" * H-I
                I.calcOutput();                                           //I GOT INPUT MUST calculate its out put i.e the output of the entire Nueral Network

                double target = dataSet[(i % rows)][cols - 1];                             //Target Value
                double error = (target - I.output) * I.output * (1.0 - I.output);
                totalError = totalError+Math.abs(error);
            }
            
            totalError = totalError/dataset.length;
            System.out.println("-----------------------------------------------------------");
            System.out.println("The Total Error for Iteration "+Iteration+" is " + totalError);
            System.out.println("------------------------------------------------------------");
            Iteration++;
        } while (totalError> 0.001);
    }

    public static class MyCSV {

        File file;
        FileReader fileReader;
        BufferedReader myReader;

        double res[][];
        double train[][];
        double test[][];

        public MyCSV(String path) throws FileNotFoundException {
            file = new File(path);
            fileReader = new FileReader(file);
            myReader = new BufferedReader(fileReader);
        }

        public double[][] readCSV() throws IOException {
            LinkedList lines = new LinkedList();
            myReader.readLine(); //FIRST LINE IS HEADER i.e X1, X2, X3, Y omit it
            String l = myReader.readLine();        //USE SECOND LINE AND soon
            while (l != null) {
                //System.out.println(l);
                lines.add(l);
                l = myReader.readLine();
            }
            fileReader.close();
            myReader.close();

            int rows = lines.size();
            int cols = ((String) lines.getFirst()).split(",").length;

            res = new double[rows][cols];

            for (int i = 0; i < rows; i++) {
                String line = (String) lines.pop();
                String parts[] = line.split(",");
                for (int j = 0; j < cols; j++) {
                    res[i][j] = Double.valueOf(parts[j].trim());
                }
            }

            //NORMALIZING THE DATA BETWEEN 0 AND 1
            double min_col_0 = min(res, 0);
            double min_col_1 = min(res, 1);
            double min_col_2 = min(res, 2);
            double min_col_3 = min(res, 3);
            double max_col_0 = max(res, 0);
            double max_col_1 = max(res, 1);
            double max_col_2 = max(res, 2);
            double max_col_3 = max(res, 3);

            for (int i = 0; i < rows; i++) {
                res[i][0] = (res[i][0] - min_col_0) / (max_col_0 - min_col_0);
                res[i][1] = (res[i][1] - min_col_1) / (max_col_1 - min_col_1);
                res[i][2] = (res[i][2] - min_col_2) / (max_col_2 - min_col_2);
                res[i][3] = (res[i][3] - min_col_3) / (max_col_3 - min_col_3);
            }

            int train_rows = (int) ((rows / 4.0) * 3.0);
            int test_rows = rows - train_rows;

            train = new double[train_rows][cols];
            test = new double[test_rows][cols];

            int i;
            for (i = 0; i < train_rows; i++) {
                train[i] = res[i];
            }
            for (int j = 0; j < test_rows; i++, j++) {
                test[j] = res[i];
            }

            return train;
        }

        private double min(double[][] array, int col) {
            double a = array[0][col];
            for (int i = 1; i < array.length; i++) {
                if (a > array[i][col]) {
                    a = array[i][col];
                }
            }
            return a;
        }

        private double max(double[][] array, int col) {
            double a = array[0][col];
            for (int i = 1; i < array.length; i++) {
                if (a < array[i][col]) {
                    a = array[i][col];
                }
            }
            return a;
        }

    }

    //THE INPUT LAYERLAYER CLASS
    public static class InputLayer {

        Neuron[] inputNuerons;

        public InputLayer(int noOfNeurons) {
            inputNuerons = new Neuron[noOfNeurons];
        }
    }
    //THE HIDDEN LAYER CLASS

    public static class HiddenLayer {

        Neuron[] hiddenNuerons;

        public HiddenLayer(int noOfNeurons) {
            hiddenNuerons = new Neuron[noOfNeurons];
        }
    }
    //THE INPUT LAYER CLASS

    public static class OutputLayer {

        Neuron[] outputNuerons;

        public OutputLayer(int noOfNeurons) {
            outputNuerons = new Neuron[noOfNeurons];
        }
    }

    //THE NEURON CLASS i.e THE CIRCULAR NODES that we have on THE DIAGRAM
    public static class Neuron {

        private String name;

        //input will be hardly use but we will save it
        private double input;
        //the output that it will generate, will be used to generate error
        private double output;
        //its bias
        private double bias;
        //its error
        private double error;

        //THIS ARE THE CONNECTION or lines in diagram THAT WILL CONNECT DIFFERENT NODES(NEURONS)
        //WE WILL NOT NOTE THE INCOMMING CONNECTION ON A NEURON
        //WE ONLY NOTE ONLY THE OUTGOING CONNECTION OF EACH NEURON
        private Connection connections[];

        //THE CONSTRUCTOR
        //EACH NUERON AT THE TIME OF CONSTRCTION ONLY KNOWS ITS NAME AND BIAS VALUe
        //NOTE THAT WHEN WE WERE CREATING THE INPUT LAYER THE BIAS WAS 0
        //FOR HIDDEN AND OUTPUT LAYERS THE BIAS WAS RANDOM NUMBER
        public Neuron(String name, double bias, int no_of_connections) {
            this.name = name;
            this.bias = bias;
            this.connections = new Connection[no_of_connections];
        }

        private void calcOutput() {
            //SIGMOD FUNCTION
            this.input = this.input + this.bias;
            //System.out.println("in = "+this.input);
            this.output = (1.0d / (1.0d + Math.exp(-this.input)));
            //System.out.println("out = "+this.output);
        }

    }

    //THE CLASS OF CONNECTION
    public static class Connection {

        private String name;

        //THE CONNECTION MUST KNOW THE NEURON IT CAME AND THE NEURON IT GO
        private Neuron inputNeuron;
        private Neuron outputNeuron;

        //THE CURRENT WEIGHT OF THE NEURON
        private double currentWeight;
        //WE WILL STORE THE UPDATED WEIGHT HERE
        //as currentWeight MUST NOT BE UPDATED AS SOON IT WE FIND IT
        private double newWieght;

        public Connection(Neuron left, Neuron right, double weight) {
            this.name = "w" + left.name + right.name;
            this.inputNeuron = left;
            this.outputNeuron = right;
            this.currentWeight = weight;
        }

    }

}