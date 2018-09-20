package com.dumb.blockchain.main;

import java.util.ArrayList;

import com.dumb.blockchain.component.Block;
import com.google.gson.GsonBuilder;

public class DumbBlockChainStarter {

	public static ArrayList<Block> blockchain = new ArrayList<Block>(); 
	public static int difficulty = 5;
	
	public static void main(String [] args) {
		Block genesisBlock = new Block("Hi im the first block", "0");
		blockchain.add(genesisBlock);
		System.out.println("Hash for block 1 : " + genesisBlock.hash);
		blockchain.get(0).mineBlock(difficulty);
		
		Block secondBlock = new Block("Yo im the second block",blockchain.get(blockchain.size()-1).hash);
		blockchain.add(secondBlock);
		System.out.println("Hash for block 2 : " + secondBlock.hash);
		blockchain.get(1).mineBlock(difficulty);
		
		Block thirdBlock = new Block("Hey im the third block",blockchain.get(blockchain.size()-1).hash);
		blockchain.add(thirdBlock);
		System.out.println("Hash for block 3 : " + thirdBlock.hash);
		blockchain.get(2).mineBlock(difficulty);
		
		System.out.println("Blockchain is Valid: " + isChainValid());
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);		
		System.out.println(blockchainJson);
	}
	
	public static Boolean isChainValid() {
		Block currentBlock; 
		Block previousBlock;
		
		//loop through blockchain to check hashes:
		for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			//compare registered hash and calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("Current Hashes not equal");			
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
		}
		return true;
	}
}
