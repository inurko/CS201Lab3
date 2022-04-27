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

    public int insert(String id, HashObject hashObject){
        long hashCode = sfold(id, hashObject.getId().getLength());
        int bucketIndex = findIndex(hashCode); //the bucket that the node belongs in

        for (int i = 0; i < 32; i++){
            //System.out.println("Bucket Array " + bucketArray[bucketIndex + i]);
      //      System.out.println("Bucket Index " + bucketIndex);
        if (bucketArray[bucketIndex + i] == null){
            bucketArray[bucketIndex + i] = hashObject;
            break;}
        }
        return bucketIndex;
    }

    public HashObject search(String id, Integer counter){
        Long hash = sfold(id, m);
        int index = findIndex(hash);
        return bucketArray[counter + index];
    }

    public HashObject remove(String id, Integer skip){
        //hash.getId()
        Long hash = sfold(id, m);
        int index = findIndex(hash);
       bucketArray[index + skip] = null;
       return bucketArray[index + skip];
    }

    public HashObject [] print(){
         HashObject [] hashArray = new HashObject [m];

         for (int i =0; i < m; i++){
             hashArray[i]= bucketArray[i];
         }
        return hashArray;
    }

    HashTable table = new HashTable() {

        @Override
        public int insert(Comparable id, Object handle) {
            return 0;
        }

        @Override
        public Object remove(Comparable sequenceID, Integer amountToSkip) {
            return null;
        }

        @Override
        public int hash(Comparable sequenceID) {
            return 0;
        }

        @Override
        public Object search(Comparable id, Integer amountToSkip) {
            return null;
        }

        @Override
        public Object[] print() {
            return new Object[0];
        }

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


