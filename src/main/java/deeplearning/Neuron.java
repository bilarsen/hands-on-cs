package deeplearning;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Neuron {

    private final int[][] synapses;

    private final int boundary;

    public Neuron(int n, int m, int boundary) {
        this.synapses = new int[n][m];
        this.boundary = boundary;
    }

    public int activationFunction(File file) {
        int sum = 0;
        try (Scanner scanner = new Scanner(file)) {
            for (int i = 0; i < synapses.length; i++) {
                for (int j = 0; j < synapses[i].length; j++) {
                    sum += scanner.nextInt() * synapses[i][j];
                }
            }
            return sum < boundary ? 1 : 0;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
