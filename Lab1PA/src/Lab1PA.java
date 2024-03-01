//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

 class Lab1PA {

    public static void main(String[] var0) {
        Lab1PA var1 = new Lab1PA();
        int var2 = Integer.parseInt(var0[0]);
        int var3 = Integer.parseInt(var0[1]);
        int var4 = Integer.parseInt(var0[2]);
        var1.homework(var2, var3, var4);
        //var1.compulsory();
    }

    void compulsory() {
        System.out.println("Hello World!");
        String[] var1 = new String[]{"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int var2 = (int)(Math.random() * 1000000.0);
        var2 *= 3;
        var2 += 21;
        var2 += 255;

        int var4;
        for(var2 *= 6; var2 > 9; var2 = var4) {
            var4 = 0;

            for(int var3 = var2; var3 > 0; var3 /= 10) {
                var4 += var3 % 10;
            }
        }

        System.out.println("Random integer n: " + var2);
        System.out.println("\"Willy-nilly, this semester I will learn \" " + var1[var2]);
    }

    public Boolean isreduc(int var1, int var2) {
        int var4;
        while(var1 > 9) {
            int var3 = 0;
            while(var1>0){
                var4=var1%10;
                var3+=var4*var4;
                var1/=10;
            }
            var1=var3;
        }

        if (var1 == var2) {
            return true;
        } else {
            return false;
        }
    }

    void homework(int var1, int var2, int var3) {
        long start =  System.currentTimeMillis();
        StringBuilder var4 = new StringBuilder();

        for(int var5 = var1; var5 <= var2; ++var5) {
            if (this.isreduc(var5, var3)) {
                var4.append(var5).append(", ");
            }
        }
        long end =  System.currentTimeMillis();
        long duration= end-start;
        System.out.println("Intervalul de numere este: " + String.valueOf(var4));
        System.out.println("Timpul de executie în milisecunde: " + duration);
    }

    void bonus() {
    }
}
