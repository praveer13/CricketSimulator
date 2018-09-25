package com.cricket.cricketOverSimulator.service;

import com.cricket.cricketOverSimulator.model.Over;
import com.cricket.cricketOverSimulator.model.Scores;
import com.cricket.cricketOverSimulator.utils.Constants;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CricketService {

    private HashMap<String,Over> userToOverMap = new HashMap<>();
    private int[] ranksArray = new int[37];
    HashMap<Integer,Scores> hmScores = new HashMap<>();
    public Over playBall(String userId) throws Exception {
        if(!userToOverMap.containsKey(userId)){
            throw new Exception("Please initiate over before playing the ball.");
        } else {
            Over currentOver = userToOverMap.get(userId);
            int prevBall = currentOver.getBallsPlayed();
            if(!Constants.ACTIVE.equals(currentOver.getMatchStatus())){
                findRank(currentOver,currentOver.getTotalScore());
                return currentOver;
            }
            Random ran = new Random();
            int randomOutput = Constants.PICK_FROM_ARRAY[ran.nextInt(Constants.PICK_FROM_ARRAY.length)];
            if(randomOutput == -1){
                currentOver.setMatchStatus(Constants.PLAYER_OUT);
                currentOver.setBallsPlayed(prevBall+1);
                findRank(currentOver,currentOver.getTotalScore());
                return currentOver;
            }else{
                currentOver.setBallsPlayed(prevBall+1);
                currentOver.setTotalScore(randomOutput+currentOver.getTotalScore());
                currentOver.getBallToRunMap().put(prevBall+1,randomOutput);
                if(prevBall >= 5){
                    currentOver.setMatchStatus(Constants.OVER_FINISHED);
                    findRank(currentOver,currentOver.getTotalScore());
                }
                return currentOver;
            }
        }
    }

    public String initiateOver(){
        Over over = new Over();
        String userID = RandomStringUtils.random(10,true,true);
        userToOverMap.put(userID,over);
        return userID;
    }

    private void findRank(Over currentOver, int score){
        Integer bucketRank = currentOver.getBucketRank();
        if(bucketRank == null){
            int currentBucketPlayers = ranksArray[36-score]+1;
            ranksArray[36-score]=currentBucketPlayers;
            currentOver.setBucketRank(currentBucketPlayers);
            bucketRank = currentBucketPlayers;
        }
        int indexInRankArr = 36-score;
        int previousRanksCount = 0;
        for(int i=0; i< indexInRankArr;i++){
            previousRanksCount+=ranksArray[i];
        }
        currentOver.setRank(bucketRank+previousRanksCount);
    }
}
