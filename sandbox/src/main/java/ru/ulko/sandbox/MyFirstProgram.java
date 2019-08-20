package ru.ulko.sandbox;

class MyFirstProgram {
	public static void main(String[] args){
		hello("world");
		hello("user");

		Point p1 = new Point(2,3);
		Point p2 = new Point(5,6);
		System.out.println("Расстрояние между двумя точками P1(" + p1.getXY() + ") и P2(" + p2.getXY() + ") равно " + distance(p1, p2));

		Square s = new Square(5);
		System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

		Rectangle r = new Rectangle(4,6);
		System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
	}

	public static void hello(String somebody) {
		System.out.println("Hello, " + somebody + "!");
	}

	public static double distance(Point p1, Point p2){
		double xa = p1.getX();
		double xb = p2.getX();
		double ya = p1.getY();
		double yb = p2.getY();

		return Math.sqrt((Math.pow((xb-xa), 2) + Math.pow((yb-ya), 2)));
	}

}