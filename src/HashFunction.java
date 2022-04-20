import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.LongToIntFunction;

public class HashFunction implements HashTable<String, HashObject>{


    /**
     * 
     * @param s
     *            is the string to be hashed
     * @param m
     *            hash table size
     * @return the hash value
     */

    private int m;

    private HashObject [] bucketArray;


    public HashFunction(int hashSize) {
        bucketArray = new HashObject[hashSize];
         m = hashSize;
    }

    public void insert(String sequenceID, int length, String sequence) throws IOException {
        long hashCode = sfold(sequenceID, length); //gets the bucket index
        MemoryManager mem = new MemoryManager(m); //creates a node for the sequence
        int bucketIndex = findIndex(hashCode); //the bucket that the node belongs in
        bucketArray[bucketIndex] = mem.insert(sequenceID, sequence, length);

    }


    HashTable table = new HashTable() {

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

        ;
        //  public ArrayList<HashNode<K,V>> bucketArray;

        private int findIndex(long hashCode) { //finds which bucket the node belongs to
            int index = (int) (hashCode % m);
            // key.hashCode() could be negative.
            index = index < 0 ? index * -1 : index;
            return index;
        }

        private long sfold(String s, int m) { //hashes the sequence
            int intLength = s.length() / 4;
            long sum = 0;
            for (int j = 0; j < intLength; j++) {
                char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
                long mult = 1;
                for (int k = 0; k < c.length; k++) {
                    sum += c[k] * mult;
                    mult *= 256;
                }
            }

            char[] c = s.substring(intLength * 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }

            sum = (sum * sum) >> 8;
            return (Math.abs(sum) % m);
        }



}


