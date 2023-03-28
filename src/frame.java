import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class frame implements ActionListener{

    JFrame jf;
    JLabel gameLabel,game,nokta,text,title,rowText,colText,sonucBoard,sonucBoardKisa;
    JButton problem1,problem2,bitir,baslat,url1Btn,url2Btn,geriBtn,olustur;
    Image imgBackground= new ImageIcon(App.class.getResource("background.jpg")).getImage();
    Font font;
    Timer tm;
    //JPanel gridBack;
    JTextField row,col;
    public int count=0;
    grid panel=new grid("http://bilgisayar.kocaeli.edu.tr/prolab2/url1.txt");
    mazeCreate create=new mazeCreate(10,10);
    public String rowSt,colSt;

    public frame(){
        jf=new JFrame("snake game");
        jf.setSize(1000, 636);
        gameLabel=new JLabel();
        gameLabel.setOpaque(true);
        gameLabel.setBackground(Color.pink);
        gameLabel.setBounds(0,0,1000,636);
        game=new JLabel();
        game.setOpaque(true);
        game.setBackground(Color.blue);
        game.setBounds(0,0,500,300);

        //FONT
        try {
            InputStream is = getClass().getResourceAsStream("PressStart2P-Regular.ttf");
            font=Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(10f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Color buttonColor = new Color (46, 31, 3);

        //title label
        title=new JLabel("Maze Game",JLabel.CENTER);
        title.setText("Maze Game");
        title.setBounds(250,25,500,50);
        title.setFont(font);
        title.setOpaque(true);
        title.setBackground(buttonColor);
        title.setForeground(Color.white);
        //row-col text
        rowText=new JLabel();
        rowText.setBounds(335, 228, 200, 60);
        rowText.setText("satir sayisi:");
        rowText.setFont(font);
        rowText.setOpaque(true);
        rowText.setBackground(buttonColor);
        rowText.setForeground(Color.white);
        rowText.setVisible(false);
        ///
        colText=new JLabel();
        colText.setBounds(335, 348, 200, 60);
        colText.setText("sütun sayisi:");
        colText.setFont(font);
        colText.setOpaque(true);
        colText.setBackground(buttonColor);
        colText.setForeground(Color.white);
        colText.setVisible(false);

        //problem button 
        problem1=new JButton("problem 1");
        problem1.setText("problem 1");
        problem1.setBounds(350,228,300,60);
        problem1.setOpaque(true);
        problem1.setBackground(buttonColor);
        problem1.setForeground(Color.white);
        problem1.setFont(font);
        problem1.addActionListener(this);
        problem2=new JButton("PROBLEM 2");
        problem2.setText("problem 2");
        problem2.setBounds(350,348,300,60);
        problem2.setOpaque(true);
        problem2.setBackground(buttonColor);
        problem2.setForeground(Color.white);
        problem2.setFont(font);
        problem2.addActionListener(this);
        //URL BUTONLARI
        url1Btn=new JButton("url 1");
        url1Btn.setText("url 1");
        url1Btn.setBounds(350,228,300,60);
        url1Btn.setOpaque(true);
        url1Btn.setBackground(buttonColor);
        url1Btn.setForeground(Color.white);
        url1Btn.setFont(font);
        url1Btn.addActionListener(this);
        url1Btn.setVisible(false);
        
        url2Btn=new JButton("url 2");
        url2Btn.setText("url 2");
        url2Btn.setBounds(350,348,300,60);
        url2Btn.setOpaque(true);
        url2Btn.setBackground(buttonColor);
        url2Btn.setForeground(Color.white);
        url2Btn.setFont(font);
        url2Btn.addActionListener(this);
        url2Btn.setVisible(false);
        //GERİ GİTME BUTONU
        geriBtn=new JButton();
        geriBtn.setBounds(25, 25, 75, 50);
        geriBtn.setText("↵");
        geriBtn.setBackground(buttonColor);
        geriBtn.setForeground(Color.white);
        geriBtn.setFont(font);
        geriBtn.addActionListener(this);
        geriBtn.setVisible(false);
        //OLUSTUR BUTONU
        olustur=new JButton("maze oluştur");
        olustur.setText("oluştur");
        olustur.setBounds(400, 525, 200, 50);
        olustur.setOpaque(true);
        olustur.setBackground(buttonColor);
        olustur.setForeground(Color.white);
        olustur.setFont(font);
        olustur.addActionListener(this);
        olustur.setVisible(false);

        //BİTİR BUTON
        bitir=new JButton("BİTİR");
        bitir.setText("bitir");
        bitir.setBounds(550, 525, 100, 50);
        bitir.setOpaque(true);
        bitir.setBackground(buttonColor);
        bitir.setForeground(Color.white);
        bitir.setFont(font);
        bitir.addActionListener(this);
        bitir.setVisible(false);
        //BASLAT BUTONU
        baslat=new JButton("BASLAT");
        baslat.setText("baslat");
        baslat.setBounds(350, 525, 100, 50);
        baslat.setOpaque(true);
        baslat.setBackground(buttonColor);
        baslat.setForeground(Color.white);
        baslat.setFont(font);
        baslat.addActionListener(this);
        baslat.setVisible(false);
        //kullanıcıdan veri alma butonu
        row=new JTextField();
        row.setBounds(565,228,100,60);
        row.setBackground(buttonColor);
        row.setFont(font);
        row.setForeground(Color.white);
        row.setVisible(false);
        col=new JTextField();
        col.setBounds(565,348,100,60);
        col.setBackground(buttonColor);
        col.setFont(font);
        col.setForeground(Color.white);
        col.setVisible(false);
        //text
        text=new JLabel("robot hareket süresi");
        text.setBounds(800,25,100,50);
        text.setOpaque(true);
        text.setBackground(buttonColor);
        text.setVisible(false);
        //sonuc tablosu
        sonucBoard=new JLabel("robot hareket süresi");
        sonucBoard.setBounds(800,100,100,100);
        sonucBoard.setOpaque(true);
        sonucBoard.setBackground(buttonColor);
        sonucBoard.setVisible(false);
        //en kısa yol sonuc tablosu
        sonucBoardKisa=new JLabel("robot hareket süresi");
        sonucBoardKisa.setBounds(800,225,100,100);
        sonucBoardKisa.setOpaque(true);
        sonucBoardKisa.setBackground(buttonColor);
        sonucBoardKisa.setVisible(false);
        ////////////////////////////////////////
        gameLabel.setIcon(new ImageIcon(imgBackground));
        JLayeredPane layers = new JLayeredPane();
        layers.setBounds(0, 0, 1000, 636);
        layers.add(gameLabel);

        ///////////////////////////////////////
        jf.add(panel);
        jf.add(create);
        jf.getContentPane().add(sonucBoardKisa);
        jf.getContentPane().add(sonucBoard);
        jf.getContentPane().add(olustur);
        jf.getContentPane().add(colText);
        jf.getContentPane().add(rowText);
        jf.getContentPane().add(col);
        jf.getContentPane().add(row);
        jf.getContentPane().add(title);
        jf.getContentPane().add(text);
        jf.getContentPane().add(geriBtn);
        jf.getContentPane().add(url1Btn);
        jf.getContentPane().add(url2Btn);
        jf.getContentPane().add(baslat);
        jf.getContentPane().add(bitir);
        jf.getContentPane().add(problem1);
        jf.getContentPane().add(problem2);
        jf.add(layers);
        panel.setVisible(false);
        create.setVisible(false);

        ////////////////////////////////////////  
        jf.getContentPane().setLayout(null);
		jf.setVisible(true); 
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.tm.stop();
        create.tm.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==problem1){
            problem1.setVisible(false);
            problem2.setVisible(false);
            url1Btn.setVisible(true);
            url2Btn.setVisible(true);
        }
        if(e.getSource()==url1Btn){
            panel.setVisible(true);
            jf.remove(panel);
            panel=new grid("http://bilgisayar.kocaeli.edu.tr/prolab2/url1.txt");
            panel.tm.stop();
            int length=panel.maze.length*20;
            panel.setBounds((1000-length)/2,(600-length)/2,length,length);
            jf.add(panel);
            bitir.setVisible(true);
            baslat.setVisible(true);
            url1Btn.setVisible(false);
            url2Btn.setVisible(false);
            geriBtn.setVisible(true);
            sonucBoard.setText("<html><b>"+"robot"+"<br/"+""+"<br/"+panel.st+"<br/"+""+"<br/"+panel.st2+"</b>"+"</html>");
            sonucBoard.setForeground(Color.white);
            sonucBoardKisa.setText("<html><b>"+"kisa yol"+"<br/"+""+"<br/"+panel.kisaYolSt+"<br/"+""+"<br/"+panel.kisaYolSt2+"</b>"+"</html>");
            sonucBoardKisa.setForeground(Color.white);
        }
        if(e.getSource()==url2Btn){
            jf.remove(panel);
            panel=new grid("http://bilgisayar.kocaeli.edu.tr/prolab2/url2.txt");
            panel.setVisible(true);
            panel.tm.stop();
            int length=panel.maze.length*20;
            panel.setBounds((1000-length)/2,(600-length)/2,length,length);
            jf.add(panel);
            bitir.setVisible(true);
            baslat.setVisible(true);
            url1Btn.setVisible(false);
            url2Btn.setVisible(false);
            geriBtn.setVisible(true);
            sonucBoard.setText("<html><b>"+"robot"+"<br/"+""+"<br/"+panel.st+"<br/"+""+"<br/"+panel.st2+"</b>"+"</html>");
            sonucBoard.setForeground(Color.white);
            sonucBoardKisa.setText("<html><b>"+"kisa yol"+"<br/"+""+"<br/"+panel.kisaYolSt+"<br/"+""+"<br/"+panel.kisaYolSt2+"</b>"+"</html>");
            sonucBoardKisa.setForeground(Color.white);
        }
        if(e.getSource()==baslat){
           panel.tm.start();
           create.tm.start();
        }
        if(e.getSource()==bitir){
            text.setText("yeey");
            text.setForeground(Color.white);
            text.setFont(font);
            text.setVisible(true);
            panel.tm.setDelay(1);
            create.tm.setDelay(1);
            //sonucBoard.setText(panel.st);
            //"<html><b>Line<br/Next line</b></html>"

            sonucBoard.setVisible(true);
            sonucBoardKisa.setVisible(true);
        }
        if(e.getSource()==geriBtn){
            panel.setVisible(false);
            baslat.setVisible(false);
            bitir.setVisible(false);
            create.setVisible(false);
            problem1.setVisible(true);
            problem2.setVisible(true);
            col.setVisible(false);
            row.setVisible(false);
            colText.setVisible(false);
            rowText.setVisible(false);
            olustur.setVisible(false);
            text.setText("");
            text.setVisible(false);
            sonucBoard.setText("");
            sonucBoard.setVisible(false);
            sonucBoardKisa.setText("");
            sonucBoardKisa.setVisible(false);

        }
        if(e.getSource()==problem2){
            problem1.setVisible(false);
            problem2.setVisible(false);
            olustur.setVisible(true);
            rowText.setVisible(true);
            colText.setVisible(true);
            row.setVisible(true);
            col.setVisible(true);
        }
        if(e.getSource()==olustur){
            rowSt=row.getText();
            int r=Integer.parseInt(rowSt);
            colSt=col.getText();
            int c=Integer.parseInt(colSt);
            olustur.setVisible(false);
            row.setVisible(false);
            col.setVisible(false);
            colText.setVisible(false);
            rowText.setVisible(false);
            jf.remove(create);
            create=new mazeCreate(r,c);
            create.setBounds(((1000-(create.maze[0].length)*20)/2),(636-(create.maze.length)*20)/2,create.maze[0].length*20,create.maze.length*20);
            create.tm.stop();
            jf.add(create);
            create.setVisible(true);
            baslat.setVisible(true);
            bitir.setVisible(true);
            geriBtn.setVisible(true);
            sonucBoard.setText("<html><b>"+"robot"+"<br/"+""+"<br/"+create.st+"<br/"+""+"<br/"+create.st2+"</b>"+"</html>");
            sonucBoard.setForeground(Color.white);
            sonucBoardKisa.setText("<html><b>"+"kisa yol"+"<br/"+""+"<br/"+create.kisaYolSt+"<br/"+""+"<br/"+create.kisaYolSt2+"</b>"+"</html>");
            sonucBoardKisa.setForeground(Color.white);
        }

    }

}