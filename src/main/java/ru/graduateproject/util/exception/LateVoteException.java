package ru.graduateproject.util.exception;

public class LateVoteException extends RuntimeException{
    public LateVoteException(){
        super("Sorry, you are not allowed to vote after deadline. Voting is over.");
    }
}
