package com.cricket.cricketOverSimulator.service;

import com.cricket.cricketOverSimulator.model.Over;
import com.cricket.cricketOverSimulator.model.Scores;
import com.cricket.cricketOverSimulator.utils.Constants;
import com.cricket.cricketOverSimulator.utils.ScoreComparator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CricketService {

    HashMap<String,Over> userToOverMap = new HashMap<>();
    TreeSet<Scores> scoreSet = new TreeSet<Scores>(new ScoreComparator());
    HashMap<Integer,Scores> hmScores = new HashMap<>();
    public Over playBall(String userId) throws Exception {
        if(!userToOverMap.containsKey(userId)){
            throw new Exception("Please initiate over before playing the ball.");
        } else {
            Over currentOver = userToOverMap.get(userId);
            int prevBall = currentOver.getBallsPlayed();
            if(!Constants.ACTIVE.equals(currentOver.getMatchStatus())){
                findRank(currentOver,userId);
                return currentOver;
            }
            Random ran = new Random();
            int randomOutput = Constants.PICK_FROM_ARRAY[ran.nextInt(Constants.PICK_FROM_ARRAY.length)];
            if(randomOutput == -1){
                currentOver.setMatchStatus(Constants.PLAYER_OUT);
                currentOver.setBallsPlayed(prevBall+1);
                findRank(currentOver,userId);
                return currentOver;
            }else{
                int runs = randomOutput;
                currentOver.setBallsPlayed(prevBall+1);
                currentOver.setTotalScore(runs+currentOver.getTotalScore());
                currentOver.getBallToRunMap().put(prevBall+1,runs);
                if(prevBall >= 5){
                    currentOver.setMatchStatus(Constants.OVER_FINISHED);
                    findRank(currentOver,userId);
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

    public void findRank(Over currentOver, String userId){

        Scores sc = hmScores.get(currentOver.getTotalScore());
        if(sc == null){
            sc = new Scores(currentOver.getTotalScore());
            scoreSet.add(sc);
            hmScores.put(currentOver.getTotalScore(),sc);
        }

        sc.getUsers().add(userId);
        Iterator<Scores> it = scoreSet.iterator();
        int rank = 1;
        while (it.hasNext()) {
            if (it.next().getScore().equals(sc.getScore())){
                currentOver.setRank(rank);
                break;
            }
            rank++;
        }
    }
}
