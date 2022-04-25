package ru.stqa.jtl.sandbox;

public class MyFirstProgram {
	
	static public void main (String[] args){
		hello("world");
		hello("user");

		Square s = new Square(5);
		System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

		Rectangle r = new Rectangle(4, 6);
		System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b +" = " + r.area());

		Point p1 = new Point(1, 1);
		Point p2 = new Point(2, 2);
		System.out.println("Вариант 1. Расстояние между точками (" + p1.x + ", " + p1.y + ") и (" + p2.x + ", " + p2.y + ") = " + String.format("%.2f", distance(p1, p2)).replace(',', '.'));
		System.out.println("Вариант 2. Расстояние между точками (" + p1.x + ", " + p1.y + ") и (" + p2.x + ", " + p2.y + ") = " + String.format("%.2f", p1.distance(p2)).replace(',', '.'));
	}

	public static void hello(String somebody) {
		System.out.println("Hello, " + somebody + "!");
	}

	public static double distance(Point p1, Point p2){
		return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
	}
}