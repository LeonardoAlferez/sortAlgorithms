import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Sorter {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        String opc;

        System.out.println("...Verifying files, just wait a few seconds...");

        //TryCatch para la lectura del archivo requerido.
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("randomValues.txt"));
            //Si encuentra el archivo, ejecutará el programa con el archivo con ese nombre
            System.out.println("Selecciona que método quieres usar\n1: QuickSort");
            opc = scanner.nextLine();

            //Se selecciona el algoritmo de ordenamiento deseado.
            if (!opc.equals("0")){
                readingFile();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            try {

                //Si no encuentra el archivo, escribe uno nuevo con números random para la ejecución correcta del programa
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("randomValues.txt"));
                String rdmvalues = "";

                //Método para llenar el String y escribir en el archivo.
                rdmvalues = randomNumbers(rdmvalues);
                bufferedWriter.write(rdmvalues);
                bufferedWriter.close();

                readingFile();



            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }


    }

    private static void readingFile(){
        File file = new File("randomValues.txt");
        Scanner sc;
        try {
            sc = new Scanner(file);
            sc.useDelimiter(",");
            int [] array = new int[10];
            int i = 0;
            while (sc.hasNext()){
                array[i] = sc.nextInt();
                i++;
            }
            System.out.println("Array before sorting: ");
            printArray(array);
            QuickSort(array);
            System.out.println("Array after sorting: ");
            printArray(array);
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }

    private static void QuickSort(int[] array) {
        QuickSort(array, 0, array.length -1);
    }

    private static void printArray(int[] array) {
        Arrays.stream(array).forEach(System.out::println);
    }

    private static void QuickSort(int[] array, int left, int right) {
        //Declaración de nuestro pivote y los punteros para la comparación en nuestro array
        if (left >= right){
            return ;
        }
        int randomPivot = new Random().nextInt(right - left) +left;
        int pivot = array[randomPivot];
        swap(array, randomPivot, right);

        int leftPointer = partition(array, left, right, pivot);

        QuickSort(array, left, leftPointer-1);
        QuickSort(array, leftPointer+1, right);

    }

    private static int partition(int[] array, int left, int right, int pivot) {
        int leftPointer = left;
        int rightPointer = right;


        while (leftPointer < rightPointer){
            //Recorrido del puntero izquierdo a la derecha
            while (array[leftPointer] <= pivot && leftPointer < rightPointer){
                leftPointer++;
            }
            //Recorrido del puntero derecho a la izquierda
            while (array[rightPointer] >= pivot && leftPointer < rightPointer ){
                rightPointer--;
            }

            //Método para realizar el intercambio de valores
            swap(array, leftPointer, rightPointer);

        }
        swap(array,leftPointer, right);
        return leftPointer;
    }

    private static void swap(int []array, int index, int subindex){
        int varTemp = array[index];
        array[index] = array[subindex];
        array[subindex] = varTemp;
    }

    //Método para llenar String
    private static String randomNumbers(String rdmvalues){
        int [] random = new int[10];
        for (int i = 0 ; i < random.length; i++) {
            random[i] = (int) (Math.random() * 100);
            rdmvalues += ""+random[i]+",";
        }return rdmvalues;
    }
}
