import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.lang.Math;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Mapper;


public class KNNMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	ArrayList<PokerHandTrain> train = new ArrayList<PokerHandTrain>();
	ArrayList<PokerHandTest> test = new ArrayList<PokerHandTest>();
	

	
	public void setup(Context context) throws IOException, InterruptedException
	{
		// error check
		System.out.println("Start of error checking.\n");
		try{

			Configuration conf = context.getConfiguration();
			FileSystem fsTrain = FileSystem.get(conf);
			// error check
			System.out.println("Finished config and file systems.\n");
			
			// only the training data should be read in the setup() method
			String filenameTrain = context.getConfiguration().get("traindata");
			// error check
			System.out.println("Finished training data set up.\n");
			
			// set up path for training data
			Path pathTrain = new Path(filenameTrain);
			// error check
			System.out.println("Finished setting up paths.\n");
			
			// update file systems
			fsTrain = pathTrain.getFileSystem(conf);
			// error check
			System.out.println("Finished updating file systems.\n");
			
			SetupTrainSet(fsTrain, pathTrain.toString());
			// error check
			System.out.println("Finished setup methods for training data.\n");
			// System.out.println("Example training data: \n");
			// System.out.println(train.toString());
				
		} catch(IOException ioe) {
			  System.err.println("Error in reading data files");
			  System.exit(-1);
			} // end try catch	
	} // end setup method 
	
	public void SetupTrainSet(FileSystem fs, String filename) throws IOException {

		// This will reference one line at a time
        	String line = null;

		try {
			
			FSDataInputStream fis = fs.open( new Path(filename));
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

			// parse each line
			while((line = reader.readLine()) != null) {
			
				// instantiate new instance of PokerHandTrain and StringTokenizer
				PokerHandTrain phTrain = new PokerHandTrain();
				StringTokenizer st = new StringTokenizer(line, ",");
				
				while (st.hasMoreElements()) {
					// first card
					phTrain.setSuit1(Integer.parseInt(st.nextToken()));
					phTrain.setRank1(Integer.parseInt(st.nextToken()));
					// second card
					phTrain.setSuit2(Integer.parseInt(st.nextToken()));
					phTrain.setRank2(Integer.parseInt(st.nextToken()));
					// third card
					phTrain.setSuit3(Integer.parseInt(st.nextToken()));
					phTrain.setRank3(Integer.parseInt(st.nextToken()));
					// fourth card
					phTrain.setSuit4(Integer.parseInt(st.nextToken()));
					phTrain.setRank4(Integer.parseInt(st.nextToken()));
					// fifth card
					phTrain.setSuit5(Integer.parseInt(st.nextToken()));
					phTrain.setRank5(Integer.parseInt(st.nextToken()));
					// identity
					phTrain.setIdentity(Integer.parseInt(st.nextToken()));
	            }

				
				train.add(phTrain);
					
				}

			//  close files
            reader.close();
			fis.close();
			System.out.println("Finished Setup");
		}

		catch (IllegalArgumentException ill) {
			System.err.println(ill.getMessage());
		}
		catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
 	} // end SetupTrainSet method
	
	
	public void SetupTestSet(FileSystem fs, String filename) throws IOException {

		// This will reference one line at a time
        	String line = null;

		try {
			
			FSDataInputStream fis = fs.open( new Path(filename));
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

			// parse each line
			while((line = reader.readLine()) != null) {
			
				// instantiate new instance of PokerHandTrain and StringTokenizer
				PokerHandTest phTest = new PokerHandTest();
				StringTokenizer st = new StringTokenizer(line, ",");
				
				while (st.hasMoreElements()) {
					// first card
					phTest.setSuit1(Integer.parseInt(st.nextToken()));
					phTest.setRank1(Integer.parseInt(st.nextToken()));
					// second card
					phTest.setSuit2(Integer.parseInt(st.nextToken()));
					phTest.setRank2(Integer.parseInt(st.nextToken()));
					// third card
					phTest.setSuit3(Integer.parseInt(st.nextToken()));
					phTest.setRank3(Integer.parseInt(st.nextToken()));
					// fourth card
					phTest.setSuit4(Integer.parseInt(st.nextToken()));
					phTest.setRank4(Integer.parseInt(st.nextToken()));
					// fifth card
					phTest.setSuit5(Integer.parseInt(st.nextToken()));
					phTest.setRank5(Integer.parseInt(st.nextToken()));
					// predicted identity 
					phTest.setPredicted(Integer.parseInt("9999"));
	            }

				
				test.add(phTest);
					
				}

			//  close files
            reader.close();
			fis.close();
			System.out.println("Finished Setup");
		}

		catch (IllegalArgumentException ill) {
			System.err.println(ill.getMessage());
		}
		catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
 	} // end SetupTestSet method
	
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		StringTokenizer st = new StringTokenizer(value.toString(), ",");
		PokerHandTest phTest = new PokerHandTest();
					
		while (st.hasMoreElements()) {
			// first card
			phTest.setSuit1(Integer.parseInt(st.nextToken()));
			phTest.setRank1(Integer.parseInt(st.nextToken()));
			// second card
			phTest.setSuit2(Integer.parseInt(st.nextToken()));
			phTest.setRank2(Integer.parseInt(st.nextToken()));
			// third card
			phTest.setSuit3(Integer.parseInt(st.nextToken()));
			phTest.setRank3(Integer.parseInt(st.nextToken()));
			// fourth card
			phTest.setSuit4(Integer.parseInt(st.nextToken()));
			phTest.setRank4(Integer.parseInt(st.nextToken()));
			// fifth card
			phTest.setSuit5(Integer.parseInt(st.nextToken()));
			phTest.setRank5(Integer.parseInt(st.nextToken()));
			// predicted identity 
			phTest.setPredicted(9999);
        }
		
		IntWritable num = new IntWritable(9999);
		System.out.println(phTest.toString());
		context.write(value, num);
	
	} // end the map method
	
	
	
	class PokerHandTrain implements WritableComparable<PokerHandTrain> {
		
		/* 
		 * The PokerHandTrain class is for holding data of the form
		 * of a training set of poker hands. There are 11 attributes.
		 * The hand consists of five cards, each having a suit and
		 * a rank. 
		 * Suit: Ordinal (1-4) representing {Hearts, Spades, Diamonds, Clubs}
		 * Rank: Numerical (1-13) representing (Ace, 2, 3, ... , Queen, King)
		 * The final number is the name of the hand.
		 * identity:
		 * 
      		Ordinal (0-9)
      		0: Nothing in hand; not a recognized poker hand 
      		1: One pair; one pair of equal ranks within five cards
      		2: Two pairs; two pairs of equal ranks within five cards
      		3: Three of a kind; three equal ranks within five cards
      		4: Straight; five cards, sequentially ranked with no gaps
      		5: Flush; five cards with the same suit
      		6: Full house; pair + different rank three of a kind
      		7: Four of a kind; four equal ranks within five cards
      		8: Straight flush; straight + flush
      		9: Royal flush; {Ace, King, Queen, Jack, Ten} + flush
      	*/
		
		
		int suit1; // suit of first card
        int rank1; // rank of first card
        int suit2; // suit of second card
        int rank2; // rank of second card
        int suit3; // suit of third card
        int rank3; // rank of third card
        int suit4; // suit of fourth card
        int rank4; // rank of fourth card
        int suit5; // suit of fifth card
        int rank5; // rank of fifth card
        int identity; // name of hand 
        
        @Override
		public String toString() {
			return "PokerHandTrain [suit1=" + suit1 + ", rank1=" + rank1
					+ ", suit2=" + suit2 + ", rank2=" + rank2 + ", suit3="
					+ suit3 + ", rank3=" + rank3 + ", suit4=" + suit4
					+ ", rank4=" + rank4 + ", suit5=" + suit5 + ", rank5="
					+ rank5 + ", identity=" + identity + "]";
		}
		
		public int getSuit1() {
			return suit1;
		}
		public void setSuit1(int suit1) {
			this.suit1 = suit1;
		}
		public int getRank1() {
			return rank1;
		}
		public void setRank1(int rank1) {
			this.rank1 = rank1;
		}
		public int getSuit2() {
			return suit2;
		}
		public void setSuit2(int suit2) {
			this.suit2 = suit2;
		}
		public int getRank2() {
			return rank2;
		}
		public void setRank2(int rank2) {
			this.rank2 = rank2;
		}
		public int getSuit3() {
			return suit3;
		}
		public void setSuit3(int suit3) {
			this.suit3 = suit3;
		}
		public int getRank3() {
			return rank3;
		}
		public void setRank3(int rank3) {
			this.rank3 = rank3;
		}
		public int getSuit4() {
			return suit4;
		}
		public void setSuit4(int suit4) {
			this.suit4 = suit4;
		}
		public int getRank4() {
			return rank4;
		}
		public void setRank4(int rank4) {
			this.rank4 = rank4;
		}
		public int getSuit5() {
			return suit5;
		}
		public void setSuit5(int suit5) {
			this.suit5 = suit5;
		}
		public int getRank5() {
			return rank5;
		}
		public void setRank5(int rank5) {
			this.rank5 = rank5;
		}
		public int getIdentity() {
			return identity;
		}
		public void setIdentity(int identity) {
			this.identity = identity;
		}

		@Override
		public void readFields(DataInput arg0) throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void write(DataOutput arg0) throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int compareTo(PokerHandTrain o) {
			// TODO Auto-generated method stub
			return 0;
		}
	
	} // end PokerHandTrain class
	
	class PokerHandTest implements WritableComparable<PokerHandTest> {
		
		/* 
		 * The PokerHandTrain class is for holding data of the form
		 * of a training set of poker hands. There are 10 attributes.
		 * The hand consists of five cards, each having a suit and
		 * a rank. 
		 * Suit: Ordinal (1-4) representing {Hearts, Spades, Diamonds, Clubs}
		 * Rank: Numerical (1-13) representing (Ace, 2, 3, ... , Queen, King)
		*/
		
		int suit1; // suit of first card
        int rank1; // rank of first card
        int suit2; // suit of second card
        int rank2; // rank of second card
        int suit3; // suit of third card
        int rank3; // rank of third card
        int suit4; // suit of fourth card
        int rank4; // rank of fourth card
        int suit5; // suit of fifth card
        int rank5; // rank of fifth card
        int predicted; // predicted name of hand
		
		@Override
		public String toString() {
			return "PokerHandTest [suit1=" + suit1 + ", rank1=" + rank1
					+ ", suit2=" + suit2 + ", rank2=" + rank2 + ", suit3="
					+ suit3 + ", rank3=" + rank3 + ", suit4=" + suit4
					+ ", rank4=" + rank4 + ", suit5=" + suit5 + ", rank5="
					+ rank5 + ", predicted= " + predicted + "]";
		}
		public int getSuit1() {
			return suit1;
		}
		public void setSuit1(int suit1) {
			this.suit1 = suit1;
		}
		public int getRank1() {
			return rank1;
		}
		public void setRank1(int rank1) {
			this.rank1 = rank1;
		}
		public int getSuit2() {
			return suit2;
		}
		public void setSuit2(int suit2) {
			this.suit2 = suit2;
		}
		public int getRank2() {
			return rank2;
		}
		public void setRank2(int rank2) {
			this.rank2 = rank2;
		}
		public int getSuit3() {
			return suit3;
		}
		public void setSuit3(int suit3) {
			this.suit3 = suit3;
		}
		public int getRank3() {
			return rank3;
		}
		public void setRank3(int rank3) {
			this.rank3 = rank3;
		}
		public int getSuit4() {
			return suit4;
		}
		public void setSuit4(int suit4) {
			this.suit4 = suit4;
		}
		public int getRank4() {
			return rank4;
		}
		public void setRank4(int rank4) {
			this.rank4 = rank4;
		}
		public int getSuit5() {
			return suit5;
		}
		public void setSuit5(int suit5) {
			this.suit5 = suit5;
		}
		public int getRank5() {
			return rank5;
		}
		public void setRank5(int rank5) {
			this.rank5 = rank5;
		}
		public int getPredicted() {
			return predicted;
		}
		public void setPredicted(int predicted) {
			this.predicted = predicted;
		}	
	
		@Override
		public void readFields(DataInput arg0) throws IOException {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void write(DataOutput arg0) throws IOException {
			// TODO Auto-generated method stub
			
		}
		@Override
		public int compareTo(PokerHandTest o) {
			// TODO Auto-generated method stub
			return 0;
		}

        
	} // end PokerHandTest class 
	
} // end KNNMapper class
