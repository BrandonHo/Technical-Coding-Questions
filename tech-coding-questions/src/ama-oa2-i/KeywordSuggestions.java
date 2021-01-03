import java.util.ArrayList;
import java.util.List;

/*  
    https://leetcode.com/problems/search-suggestions-system/
*/
public class KeywordSuggestions {

    public void Test() {

        String[] testProducts1 = new String[] { "mobile", "mouse", "moneypot", "monitor", "mousepad" };
        String searchWord1 = "mouse";

        String[] testProducts2 = new String[] { "havana" };
        String searchWord2 = "havana";

        String[] testProducts3 = new String[] { "bags", "baggage", "banner", "box", "cloths" };
        String searchWord3 = "bags";

        String[] testProducts4 = new String[] { "havana" };
        String searchWord4 = "tatiana";

        DisplayResults(Solve(testProducts1, searchWord1));
        DisplayResults(Solve(testProducts2, searchWord2));
        DisplayResults(Solve(testProducts3, searchWord3));
        DisplayResults(Solve(testProducts4, searchWord4));
    }

    public void DisplayResults(List<List<String>> results) {

        for (List<String> result : results) {

            if (result.size() != 0) {
                for (String keyword : result)
                    System.out.print(keyword + " ");
                System.out.println();
            }
        }
        System.out.println();
    }

    public class TrieNode {
        public char val;

        public TrieNode[] nodes;
        public boolean isWord = false;
        public int productIndex;

        public TrieNode(char val) {
            this.val = val;
            nodes = new TrieNode[26];
        }
    }

    /*
     * This is a solution that uses a Trie data structure + DFS for finding the
     * appropriate words.
     * 
     * Time Complexity: O(n) + O(m), where n refers to the total number of
     * characters in the products array, and m refer to the size of searchWord.
     * 
     * - O(n) refers to building the trie with n characters
     * 
     * - O(!m) refers to querying for three words per m-size search word via DFS.
     * Since it is at most 3 words, that part is considered constant.
     * 
     * Space Complexity: O(26n) or ~O(n), where n refers to the total number of
     * characters in the product array. Each trie node (per character) can
     * potentially store up to 26 different child trie nodes.
     */
    public List<List<String>> Solve(String[] products, String searchWord) {

        // Edge case
        if (products.length == 0)
            return new ArrayList<List<String>>();

        // Create product trie using the product names
        TrieNode productTrie = new TrieNode(' ');
        TrieNode tempRoot = productTrie;
        for (int productIndex = 0; productIndex < products.length; productIndex++)
            AddStringToTrie(tempRoot, products[productIndex], productIndex);

        // Find the appropriate words for each of the substrings
        ArrayList<List<String>> results = new ArrayList<List<String>>();
        for (int searchWordLength = 1; searchWordLength <= searchWord.length(); searchWordLength++)
            results.add(GetKeywordsUsingSearchWord(productTrie, products, searchWord.substring(0, searchWordLength)));
        return results;
    }

    public List<String> GetKeywordsUsingSearchWord(TrieNode productTrie, String[] products, String searchWord) {

        // Instantiate list to store result from querying product trie
        ArrayList<String> result = new ArrayList<String>();

        // Perform DFS to find the root node that corresponds with search word
        TrieNode tempRoot = productTrie;
        for (int searchIndex = 0; searchIndex < searchWord.length(); searchIndex++) {
            // If node exists -> change to it
            if (tempRoot.nodes[searchWord.charAt(searchIndex) - 'a'] != null)
                tempRoot = tempRoot.nodes[searchWord.charAt(searchIndex) - 'a'];
            // If there is a missing node, then return empty
            else
                return result;
        }

        // With the found root note, add all words from here + return result
        AddThreeWordsFromTrieNode(tempRoot, products, result);
        return result;
    }

    public void AddStringToTrie(TrieNode trieNode, String stringToBeAdded, int productIndex) {

        // Create/Navigate to appropriate node with regards to chars of the string
        for (int stringIndex = 0; stringIndex < stringToBeAdded.length(); stringIndex++) {

            char nextChar = stringToBeAdded.charAt(stringIndex);
            if (trieNode.nodes[nextChar - 'a'] == null)
                trieNode.nodes[nextChar - 'a'] = new TrieNode(nextChar);
            trieNode = trieNode.nodes[nextChar - 'a'];
        }

        // Update word status and product index of the specified node
        trieNode.isWord = true;
        trieNode.productIndex = productIndex;
    }

    public void AddThreeWordsFromTrieNode(TrieNode productTrie, String[] products, ArrayList<String> result) {

        // If space in list, update string and add word if appropriate
        if (result.size() < 3)
            if (productTrie.isWord)
                result.add(products[productTrie.productIndex]);

        // Stop at three words
        if (result.size() == 3)
            return;

        // Navigate to all child trie nodes to potentially add words to result
        for (int nodeIndex = 0; nodeIndex < productTrie.nodes.length; nodeIndex++)
            if (productTrie.nodes[nodeIndex] != null)
                AddThreeWordsFromTrieNode(productTrie.nodes[nodeIndex], products, result);
    }
}
