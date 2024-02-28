//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

                System.out.println("Hello World!");
                String languages[] = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
                 int n = (int) (Math.random() * 1_000_000);
                 n = n*3;
                 n=n+0b10101;
                 n=n+0xFF;
                 n=n*6;
                 int s=n;
                 int k;
                while(n>9)
                 {
                     k=0;
                     s=n;
                     while(s>0)
                     {
                         k=k+s%10;
                         s=s/10;
                     }
                     n=k;
                 }
                System.out.println("Random integer n: " + n);
        System.out.println("\"Willy-nilly, this semester I will learn \" " + languages[n]);

            }
        }

