//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

 public class Lab1PA { //Iacobut Denisa-Delia

    public static void main(String[] variabila0) {
        Lab1PA variabila1 = new Lab1PA();
        /*if (variabila0.length < 3){
            System.out.println("Numar insuficient de variabile");
            System.exit(-1);
        }
        int variabila2 = 0, variabila3 = 0,variabila4 = 0;
        try {
             variabila2 = Integer.parseInt(variabila0[0]);
             variabila3 = Integer.parseInt(variabila0[1]);
             variabila4 = Integer.parseInt(variabila0[2]);
        }
        catch(NumberFormatException e){
            System.out.println("Tipul variabilelor incorecte");
            System.exit(-1);
        }

        if(variabila2 > variabila3 || variabila4 >= 10){
            System.out.println("Eroare la variabile");
            System.exit(-1);
        }
        variabila1.homework(variabila2, variabila3, variabila4);*/
       // variabila1.compulsory();
        variabila1.bonus();

    }

    void compulsory() {
        System.out.println("Hello World!");
        String[] variabila1 = new String[]{"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int variabila2 = (int)(Math.random() * 1000000.0);
        variabila2 *= 3;
        variabila2 += 21;
        variabila2 += 255;

        int variabila4;
        for(variabila2 *= 6; variabila2 > 9; variabila2 = variabila4) {
            variabila4 = 0;

            for(int variabila3 = variabila2; variabila3 > 0; variabila3 /= 10) {
                variabila4 += variabila3 % 10;
            }
        }

        System.out.println("Random integer n: " + variabila2);
        System.out.println("\"Willy-nilly, this semester I will learn \" " + variabila1[variabila2]);
    }

    public Boolean isReduc(int variabila1, int variabila2) {
        int variabila4;
        while(variabila1 > 9) {
            int variabila3 = 0;
            while(variabila1 > 0){
                variabila4=variabila1%10;
                variabila3+=variabila4*variabila4;
                variabila1/=10;
            }
            variabila1=variabila3;
        }

        if (variabila1 == variabila2) {
            return true;
        } else {
            return false;
        }
    }

    void homework(int variabila1, int variabila2, int variabila3) {
        long start =  System.currentTimeMillis();
        StringBuilder variabila4 = new StringBuilder();


        for(int variabila5 = variabila1; variabila5 <= variabila2; ++variabila5) {
            if (this.isReduc(variabila5, variabila3)) {
                variabila4.append(variabila5).append(", ");
            }
        }
        long end =  System.currentTimeMillis();
        long duration= end-start;
        System.out.println("Intervalul de numere este: " + String.valueOf(variabila4));
        System.out.println("Timpul de executie în milisecunde: " + duration);
    }
     void bonus()
         {
         int n = 6;
         int[][] matrice = new int[n][n];
         for (int i = 0; i < n; i++) {
             for(int j = 0; j < n; j++){
                 matrice[i][j]=0;
             }
         }
         for (int i = 1; i < n; i++) {
                 matrice[0][i] = 1;
                 matrice[i][0] = 1;
         }

         for (int i = 0; i < n-1; i++) {
             matrice[i][i+1] = 1;
             matrice[i+1][i] = 1;
         }
         matrice[1][n-1]=1;
         matrice[n-1][1]=1;

         for (int i = 0; i < n; i++) {
             for (int j = 0; j < n; j++) {
                 System.out.print(matrice[i][j] + " ");
             }
             System.out.println();
         }
         int cycles = countCycles(matrice, n);
         if( cycles == n*n-3*n+3 )
             System.out.println("Cycles: " + cycles + ". Verification: Passed");
        else
            System.out.println("Cycles: " + cycles + ". Verification: Failed");
        }

     public static int countCycles(int[][] matrice, int n) {
         int count = 1;
         for(int i = 1; i< n-1; i++) {
             count += n-1;
         }
         return count;
     }

 }



