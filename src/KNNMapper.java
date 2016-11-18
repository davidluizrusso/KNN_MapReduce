import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import javax.naming.Context;
import org.apache.hadoop.conf.Configuration;
import javax.xml.soap.Text;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;


public class KNNMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	public void setup (Context context) {

		
        

	} // end setup method
	
	
	
	class PokerHandTrain {
		
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
        int identity; // name of hand of card
        
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
	
	} // end PokerHandTrain class
	
	class PokerHandTest {
		
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
		
		@Override
		public String toString() {
			return "PokerHandTest [suit1=" + suit1 + ", rank1=" + rank1
					+ ", suit2=" + suit2 + ", rank2=" + rank2 + ", suit3="
					+ suit3 + ", rank3=" + rank3 + ", suit4=" + suit4
					+ ", rank4=" + rank4 + ", suit5=" + suit5 + ", rank5="
					+ rank5 + "]";
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

        
	} // end PokerHandTest class 
	
} // end KNNMapper class
