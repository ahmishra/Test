/*
The algorithm generates an animation of a 3D spinning donut shape using ASCII characters by plotting a series of points in 3D space 
and then projecting them onto a 2D plane. The animation is created by changing the values of the variables A and B in each iteration
of the while loop, which affects the projection of the points onto the 2D plane and creates the illusion of rotation.
The algorithm starts by defining the static variables A and B with the initial value of 1. 
The program checks the operating system it runs on, whether Windows or not, and uses "cls" command for windows and "clear"
command for other systems to clear the console. Then, in each iteration of the while loop, the values of A and B are incremented by 0.07
and 0.03 respectively. Then the algorithm calculates the cosine and sine of A and B, which are used for the projection calculations later.
The algorithm creates two arrays, one of chars and one of doubles, both with a length of 1760. The char array is used to store the ASCII
characters that will be used to display the donut shape and the double array is used to store the depth of each point in the donut shape.
The algorithm then iterates over two nested loops, one for theta and one for phi, to calculate the coordinates of each point in 3D space.
The algorithm uses the current values of A and B, as well as the cosine and sin of theta and phi (golden ratio), to calculate the projection of each point
onto the 2D plane. The algorithm then uses the x and y coordinates of the projected point to determine its position on the console
and assigns the appropriate ASCII character from the char array to that position. If the depth of the point is greater than the
previous point at that position, the depth is updated in the double array.

Finally, the algorithm prints the char array to the console and waits for 5 milliseconds before repeating the process to create the animation.
*/

import java.util.Scanner;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

class Donut {
	static double A = 1;
	static double B = 1;
    

	public static void main(String[] args) throws InterruptedException, IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Donut-rotation FPS: ");
        int fps = sc.nextInt();
        int ms = 1000/fps;


		final boolean isWindows = System.getProperty("os.name").contains("Windows");
        
        while(true) {
			if (isWindows) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				new ProcessBuilder("clear").inheritIO().start().waitFor();
			}

			A += 0.07;
			B += 0.03;

			double cA = Math.cos(A), sA = Math.sin(A), cB = Math.cos(B), sB = Math.sin(B);

			final char[] b = new char[1760];
			final double[] z = new double[1760];

			for (int k = 0; k < 1760; k++) {
				b[k] = k % 80 == 79 ? '\n' : ' ';
				z[k] = 0;
			}

			for (double j = 0; j < 6.28; j += 0.07) {
				double ct = Math.cos(j), st = Math.sin(j);
				for (double i = 0; i < 6.28; i += 0.02) {
					double sp = Math.sin(i), cp = Math.cos(i), h = ct + 2, D = 1 / (sp * h * sA + st * cA + 5), t = sp * h * cA - st * sA;
					int x = (int) (40 + 30 * D * (cp * h * cB - t * sB)), y = (int) (12 + 15 * D * (cp * h * sB + t * cB)), N = (int) (8 * ((st * sA - sp * ct * cA) * cB - sp * ct * sA - st * cA - cp * ct * sB)), o = x + 80 * y;
					
                    if (y < 22 && y >= 0 && x >= 0 && x < 79 && D > z[o]) {
						z[o] = D;
						b[o] = ".,-~:;=!*#$@".charAt(Math.max(N, 0));
					}
				}
			}
			System.out.println(String.valueOf(b));
			TimeUnit.MILLISECONDS.sleep(ms);
		}
	}
}
