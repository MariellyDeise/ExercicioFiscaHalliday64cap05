import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Grafico implements ActionListener{

    JTextField JTextField_m1;
    JTextField JTextField_m2;
    JTextField JTextField_angulo;
    JTextField JTextField_F;

    JLabel JLabel_resultado_tracao;
    JLabel JLabel_resultado_aceleracao_m1;
    JLabel JLabel_resultado_aceleracao_m2;
    JLabel JLabel_resultado_forca_max;

    double aceleracao_m1;
    double aceleracao_m2;
    double tracao;

    boolean possivel_calc_tracao;

    ImageIcon img = new ImageIcon(getClass().getResource("Figura.png"));// inserir a imagem.
    JLabel imagem = new JLabel(img);

    public Grafico(){

    JFrame myJframe = new JFrame();// cria uma caixa
    myJframe.setVisible(true);
    myJframe.setSize(515, 450);// regula o tamanho da caixa.
    myJframe.setResizable(false);
    myJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// quando fecha a caixa o programa finaliza.
    myJframe.setLocationRelativeTo(null);
    myJframe.setLayout(null);

    Font font = new Font("Arial", Font.BOLD, 12);// configuração da varável do tipo Font.

    JButton Jbutton_calcular = new JButton("Calcular");
    Jbutton_calcular.setBounds(350, 230, 80, 20);
    myJframe.add(Jbutton_calcular);
    Jbutton_calcular.setFont(font);

    Jbutton_calcular.addActionListener(this);

    JLabel JLabel_m1 = new JLabel("m1");
    JLabel_m1.setBounds(60, 185, 25, 20);
    myJframe.add(JLabel_m1);
    JLabel_m1.setFont(font);

    JTextField_m1 = new JTextField();
    JTextField_m1.setBounds(80, 185, 60, 20);
    myJframe.add(JTextField_m1);
    JTextField_m1.setFont(font);

    JLabel JLabel_m2 = new JLabel("m2");
    JLabel_m2.setBounds(160, 185, 25, 20);
    myJframe.add(JLabel_m2);
    JLabel_m2.setFont(font);

    JTextField_m2 = new JTextField();
    JTextField_m2.setBounds(180, 185, 60, 20);
    myJframe.add(JTextField_m2);
    JTextField_m2.setFont(font);

    JLabel JLabel_F = new JLabel("F");
    JLabel_F.setBounds(260, 185, 25, 20);
    myJframe.add(JLabel_F);
    JLabel_F.setFont(font);

    JTextField_F = new JTextField();
    JTextField_F.setBounds(270, 185, 60, 20);
    myJframe.add(JTextField_F);
    JTextField_F.setFont(font);

    JLabel JLabel_angulo = new JLabel("\u03B8");//unicode da letra .
    JLabel_angulo.setBounds(360, 185, 25, 20);
    myJframe.add(JLabel_angulo);
    JLabel_angulo.setFont(font);

    JTextField_angulo = new JTextField();
    JTextField_angulo.setBounds(370, 185, 60, 20);
    myJframe.add(JTextField_angulo);
    JTextField_angulo.setFont(font);

    JLabel exercicio = new JLabel("Considerando a imagem acima introduza as informacoes pedidas abaixo:");
    exercicio.setBounds(50, 144, 500, 20);
    myJframe.add(exercicio);
    exercicio.setFont(font);
   
    myJframe.add(imagem);
    imagem.setBounds(120, 18, img.getIconWidth(), img.getIconHeight());// Formatar a imagem.

    JLabel JLabel_tracao = new JLabel("Tracao da corda:");
    JLabel_tracao.setBounds(50, 260, 100, 20);
    myJframe.add(JLabel_tracao);
    JLabel_tracao.setFont(font);

    JLabel_resultado_tracao = new JLabel("__");
    JLabel_resultado_tracao.setBounds(160, 260, 300, 20);
    myJframe.add(JLabel_resultado_tracao);
    JLabel_resultado_tracao.setFont(font);

    JLabel JLabel_aceleracao_m1 = new JLabel("Aceleracao m1:");
    JLabel_aceleracao_m1.setBounds(50, 290, 100, 20);
    myJframe.add(JLabel_aceleracao_m1);
    JLabel_aceleracao_m1.setFont(font);

    JLabel_resultado_aceleracao_m1 = new JLabel("__");
    JLabel_resultado_aceleracao_m1.setBounds(160, 290, 60, 20);
    myJframe.add(JLabel_resultado_aceleracao_m1);
    JLabel_resultado_aceleracao_m1.setFont(font);

    JLabel JLabel_aceleracao_m2 = new JLabel("Aceleracao m2:");
    JLabel_aceleracao_m2.setBounds(50, 320, 100, 20);
    myJframe.add(JLabel_aceleracao_m2);
    JLabel_aceleracao_m2.setFont(font);

    JLabel_resultado_aceleracao_m2 = new JLabel("__");
    JLabel_resultado_aceleracao_m2.setBounds(160, 320, 60, 20);
    myJframe.add(JLabel_resultado_aceleracao_m2);
    JLabel_resultado_aceleracao_m2.setFont(font);

    JLabel Jlabel_forca_max = new JLabel("Modulo da forca horizontal maxima:");
    Jlabel_forca_max.setBounds(50, 350, 390, 20);
    myJframe.add(Jlabel_forca_max);
    Jlabel_forca_max.setFont(font);

    JLabel_resultado_forca_max = new JLabel("__");
    JLabel_resultado_forca_max.setBounds(280, 350,60, 20);
    myJframe.add(JLabel_resultado_forca_max);
    JLabel_resultado_forca_max.setFont(font);


    myJframe.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        double m1 = Double.parseDouble(JTextField_m1.getText());
        double m2 = Double.parseDouble(JTextField_m2.getText());
        double F = Double.parseDouble(JTextField_F.getText());
        double angulo = Double.parseDouble(JTextField_angulo.getText());
        double comparacao = 0.0;

        angulo = angulo * (3.14159265 / 180.0); // converte angulo para radiano.

        if(m1 > 500 || m2 > 500){
            QuebrouASuperficie();
        }else{
            if(angulo < 0){
                Erro();
            }else if(angulo > 90){
                angulo = 90;
                comparacao = m1 * ((9.8) * Math.sin(angulo));
            }else{
                comparacao = m1 * ((9.8) * Math.sin(angulo));
            }
    
            if(F >= comparacao){
                aceleracao_m1 = F / m1;
                aceleracao_m2 = (9.8) * Math.sin(angulo);
                possivel_calc_tracao = false;
            }else{
                aceleracao_m1 = (9.8) * Math.sin(angulo);
                aceleracao_m2 = aceleracao_m1;
                tracao = m2 * (m1 * 9.8 * Math.sin(angulo) - F);
                tracao = tracao / (m1 + m2);
                possivel_calc_tracao = true;
            }
        }

        JLabel_resultado_aceleracao_m1.setText(" " + aceleracao_m1);
        JLabel_resultado_aceleracao_m2.setText(" " + aceleracao_m2);

        if(possivel_calc_tracao == true){
            JLabel_resultado_tracao.setText(" " + tracao);
        }else{
            JLabel_resultado_tracao.setText(" 0");
        }

        JLabel_resultado_forca_max.setText(" " + comparacao);
    }

    public void Erro(){
        JOptionPane.showMessageDialog(null,"O angulo da rampa nao pode ser menor que 0.", "Erro!", JOptionPane.ERROR_MESSAGE);
    }

    public void QuebrouASuperficie(){
        JOptionPane.showMessageDialog(null,"Voce quebrou a rampa, a massa das caixas não podem ser maior que 500Kg.", "Erro!", JOptionPane.ERROR_MESSAGE);
    }
    
}
