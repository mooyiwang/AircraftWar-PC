package GUI_panel;

import edu.hitsz.DAO_ranking.GameRecord;
import edu.hitsz.DAO_ranking.GameRecordDAO;
import edu.hitsz.DAO_ranking.GameRecordDAOImple;
import edu.hitsz.application.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class BoardPanel {
    public JPanel baseBoardPanel;
    private JButton deleteButton;
    private JTable rankingTable;
    private JPanel midPanel;
    private JScrollPane rankingTableScroll;
    private JPanel tabelTitlePanel;
    private JPanel upperPanel;
    private JLabel gameLevelLabel;
    private JLabel tableNameLabel;
    private JPanel lowerPanel;
    private GameRecordDAO gameRecordDAO;
    private DefaultTableModel model;

    public BoardPanel() throws IOException, InterruptedException {


//        switch (Main.GAME_LEVEL){
//            case 0:
//                gameLevelLabel.setText("Game Level: EASY");
//            case 1:
//                gameLevelLabel.setText("Game Level: MEDIUM");
//            case 2:
//                gameLevelLabel.setText("Game Level: HARD");
//            default:
//        }
        if(Main.GAME_LEVEL==0)  {
            gameLevelLabel.setText("Game Level: EASY");
        }
        else if(Main.GAME_LEVEL==1){
            gameLevelLabel.setText("Game Level: MEDIUM");
        }
        else{
            gameLevelLabel.setText("Game Level: HARD");
        }

        String name = JOptionPane.showInputDialog(baseBoardPanel,("游戏结束,你的得分为"+Integer.toString(Main.SCORE)+"分。\n请输入名字记录得分（请不要有空格）："),"输入",JOptionPane.QUESTION_MESSAGE);
        gameRecordDAO = new GameRecordDAOImple();
        gameRecordDAO.importRecords();
        if(name!=null){
            gameRecordDAO.addRecord(Main.SCORE, name);
        }
        List<GameRecord> gameRecordList = gameRecordDAO.getAllRecords();
        makeTable(gameRecordList);

        gameRecordDAO.exportRecords();





        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = rankingTable.getSelectedRow();
                if(row != -1){
                    int userOption = JOptionPane.showConfirmDialog(baseBoardPanel,"是否确定删除此条记录？","删除",JOptionPane.OK_OPTION,JOptionPane.QUESTION_MESSAGE);
                    if(userOption == JOptionPane.OK_OPTION){
                        model.removeRow(row);
                        try {
                            gameRecordDAO.deleteRecordbyRank(row+1);
                            gameRecordDAO.exportRecords();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        synchronized (Main.lock){
                            Main.lock.notify();
                        }
                    }
                }
                System.out.println(row);
            }
        });
    }

    public void makeTable(List<GameRecord> gameRecordList){
        String[] coloumName = {"Ranking","Name","Score","Time"};
        String[][] data = new String[gameRecordList.size()][4];

        int i=0;
        for(GameRecord record:gameRecordList){
            data[i] = (new String[]{Integer.toString(i+1),
                    record.getName(),
                    Integer.toString(record.getScore()),
                    record.getTime()});
            i++;
        }

        model = new DefaultTableModel(data, coloumName){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        rankingTable.setModel(model);

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        JFrame frame = new JFrame("BoardPanel");
        frame.setContentPane(new BoardPanel().baseBoardPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
