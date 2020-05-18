package mayin.tarlasi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class mayintarlasi implements ActionListener {

    JFrame frame;
    JButton reset;
    JButton[][] box;
    int[][] mayin;
    Container grid;
    final int mayınlar = 10;
    int a = 0;
    int b = 0;
    public mayintarlasi() {
    	// initiliaze
    	grid = new Container();
    	mayin = new int[10][10];
    	box = new JButton[10][10];
    	reset = new JButton("Tekrar Oyna");
    	frame = new JFrame("MayınTarlası");
    	
    	
    	frame.setSize(500, 600);
        frame.setLayout(new BorderLayout());
        frame.add(reset, BorderLayout.NORTH);
        reset.addActionListener(this);
        grid.setLayout(new GridLayout(10, 10));
        for (JButton[] box1 : box) {
            for (int j = 0; j < box[0].length; j++) {
                box1[j] = new JButton();
                box1[j].addActionListener(this);
                grid.add(box1[j]);
            }
        }
        frame.add(grid, BorderLayout.CENTER);
        mayinata();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

   public static void main(String[] args) {
        mayintarlasi mayintarlasi = new mayintarlasi();
    }

    public void mayinata() {
        ArrayList<Integer> diz = new ArrayList<>();
        for (int i = 0; i < mayin.length; i++) {
            for (int j = 0; j < mayin[0].length; j++) {
                diz.add(i * 100 + j);
            }
        }
        mayin = new int[10][10];
        for (int i = 0; i < 10; i++) {
            int bomp = (int) (Math.random() * diz.size());
            mayin[diz.get(bomp) / 100][diz.get(bomp) % 100] = mayınlar;
            diz.remove(bomp);

        }

        for (int i = 0; i < mayin.length; i++) {
            for (int j = 0; j < mayin[0].length; j++) {

                if (mayin[i][j] != mayınlar) {
                    int komsumayın = 0;
                    if (i > 0 && j > 0 && mayin[i - 1][j - 1] == mayınlar) {
                        komsumayın++;

                    }
                    if (j > 0 && mayin[i][j - 1] == mayınlar) {
                        komsumayın++;
                    }
                    if (i < mayin.length - 1 && j < mayin[0].length - 1 && mayin[i + 1][j + 1] == mayınlar) {
                        komsumayın++;
                    }
                    mayin[i][j] = komsumayın;
                }
            }
        }

    }

    public void theend() {
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box[0].length; j++) {
                if (box[i][j].isEnabled()) {
                    if (mayin[i][j] != mayınlar) {
                        box[i][j].setText(mayin[i][j] + "");
                        box[i][j].setEnabled(false);
                    } else {
                        box[i][j].setText("X");
                        box[i][j].setEnabled(false);
                    }
                }
            }
        }
    }

    public void kontrol(ArrayList<Integer> sorun) {

        if (sorun.isEmpty()) {
           
        } else {
            sorun.stream().map((Integer _item) -> {
                int a1 = sorun.get(0);
                return _item;
            }).forEachOrdered((Integer _item) -> {
                int b1 = sorun.get(0);
            });
            sorun.remove(0);
            //sol tarafı kontrol ediyoruz
            if(mayin[a][b]==0){ // sıfırları kontrol ediyoruz
                if(a>0 && b>0){ // sıfır değilse komşuları kontrol ediyoruz
                	String str = String.format("",mayin[a-1][b-1]);
                    box[a-1][b-1].setText(str);
                    box[a-1][b-1].setEnabled(false);
                    if(mayin[a-1][b-1]==0){
                       
                        sorun.add((a-1)*100 +(b-1));
                    }
                    
                }
                // üst kısmı kontrol ediyoruz
                //iki yada üç bomba komşularını kontrol ediyoruz
                // a sabit kalıyor b değişiyor
                if (b>0){
                    box[a][b-1].setText(mayin[a][b-1]+ "");
                    box[a][b-1].setEnabled(false);
                     if(mayin[a][b-1]==0){
                       
                        sorun.add(a*100 +(b-1));
                    }
                }
                // a bir artma durumu
                // sağ taraf için
                if(a< mayin.length-1 && b>0 ){
                    box[a+1][b-1].setText(mayin[a+1][b-1]+ "");
                    box[a+1][b-1].setEnabled(false);
                    if(mayin[a+1][b-1]==0){
                       
                        sorun.add((a+1)*100 +(b-1));
                    }
                    
                }
                // sol taraf için b aynı kalıyor
                if(a>0 ){ 
                    box[a-1][b].setText(mayin[a-1][b]+ "");
                    box[a-1][b].setEnabled(false);
                    if(mayin[a-1][b]==0){
                       
                        sorun.add((a-1)*100 +b);
                    }
                    
                }
                
                
                // sağ taraf için
                if(a< mayin.length-1  ){
                    box[a+1][b].setText(mayin[a+1][b]+ "");
                    box[a+1][b].setEnabled(false);
                    if(mayin[a+1][b]==0){
                       
                        sorun.add((a+1)*100 +b);
                    }
                    
                }
                // solt alt köşe için b ve mayınlar karışaltırılması
                if(a>0 && b < mayin[0].length-1){ 
                    box[a-1][b+1].setText(mayin[a-1][b+1]+ "");
                    box[a-1][b+1].setEnabled(false);
                    if(mayin[a-1][b+1]==0){
                       
                        sorun.add((a-1)*100 +(b+1));
                    }
                    
                }
                
                // alt köşe için
                if (b <mayin[0].length-1 ){
                    box[a][b+1].setText(mayin[a][b+1]+ "");
                    box[a][b+1].setEnabled(false);
                     if(mayin[a][b+1]==0){
                       
                        sorun.add(a*100 +(b+1));
                    }
                }
               
                //alt  sağ köşe için
                if(a< mayin.length-1 && b <mayin[0].length-1 ){
                    box[a+1][b+1].setText(mayin[a+1][b+1]+ "");
                    box[a+1][b+1].setEnabled(false);
                    if(mayin[a+1][b+1]==0){
                       
                        sorun.add((a+1)*100 +(b+1));
                    }
                    
                }
            }
            if(sorun != null) ;
            	kontrol(sorun);
        }
  
    }
    
    public void win(){
        boolean win =true;
        for(int i= 0; i<mayin.length;i++){
        for(int j=0 ;  j<mayin[0].length;j++){
            if(mayin[i][j]!=mayınlar && box[i][j].isEnabled()==true){
                win=false;}
            }
        }
        
        if(win==true){
            JOptionPane.showMessageDialog(frame,"Kazandın");
        }
    }

    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(reset)) {
            for (JButton[] box1 : box) {
                for (int j = 0; j < box[0].length; j++) {
                    box1[j].setEnabled(true);
                    box1[j].setText("");
                }
            }

            mayinata();
        } else {
            for (int i = 0; i < box.length; i++) {
                for (int j = 0; j < box[0].length; j++) {
                    if (e.getSource().equals(box[i][j])) {
                        switch (mayin[i][j]) {
                            case mayınlar:
                                box[i][j].setForeground(Color.DARK_GRAY);
                                box[i][j].setText("X");
                                theend();
                                break;
                            case 0:
                                box[i][j].setText(mayin[i][j]+"");
                                box[i][j].setEnabled(false);
                                ArrayList<Integer>sorun= new ArrayList <>();
                                sorun.add(i*100+j);
                                kontrol(sorun);
                                win();
                                break;
                            default:
                                box[i][j].setText(mayin[i][j] + "");
                                box[i][j].setEnabled(false);
                                win();
                                break;
                        }
                    }
                }
            }
        }
    }

}

