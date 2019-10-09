import java.io.FileWriter;
import java.io.IOException; 


public class setStoreStrategy {

	private Strategy strategy;
	
	public setStoreStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
	public void executeStore(String a) {
		strategy.Store(a);
	}
	
	
}
// interface that defines the method store
interface Strategy {
	public void Store(String a);
}

// NoSQL store class
class NoSQL implements Strategy {
	
	// document store
	public void Store(String a) {
		
		try {
			FileWriter file = new FileWriter("DocumentStore.txt");
			file.write(a);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Document Store.");
	}
	
}
	// Relational store class
class Relational implements Strategy {
	// table store
	public void Store(String a) {
		
		try {
			FileWriter file = new FileWriter("TableStore.txt");
			file.write(a);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Table Store.");
	}
	
}
// Graph store class
class Graph implements Strategy {
	// node store
	public void Store(String a) {
		
		try {
			FileWriter file = new FileWriter("NodeStore.txt");
			file.write(a);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Node Store.");
	}
	
}
// driver class
class Client {
	
	public static void main(String args[]) {
		
		String a = "Data!";
		
		setStoreStrategy storestrategy = new setStoreStrategy(new NoSQL());
		storestrategy.executeStore(a);

		storestrategy = new setStoreStrategy(new Relational());
		storestrategy.executeStore(a);

		storestrategy = new setStoreStrategy(new Graph());
		storestrategy.executeStore(a);
	}
	
}
