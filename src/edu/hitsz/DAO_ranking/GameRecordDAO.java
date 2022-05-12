package edu.hitsz.DAO_ranking;

import java.io.IOException;
import java.util.List;

/**
 * @author muyi_WANG
 */
public interface GameRecordDAO {

    List<GameRecord> getAllRecords();




    void addRecord(int score, String name);

    void deleteRecordbyRank(int rank);
    void exportRecords() throws IOException;
    void importRecords() throws IOException;
    void printRecords();
    void clearAllRecords();




}
