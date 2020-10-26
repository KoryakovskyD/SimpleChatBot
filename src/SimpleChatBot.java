import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Дмитрий on 26.10.2020.
 */
public class SimpleChatBot extends JFrame implements ActionListener{

    final String TITLE = "Chatbot: chatbot simple";
    final int LOCATION = 200;
    final int WIDTH = 350;
    final int HEIGHT = 450;

    JTextArea dialog;
    JCheckBox bot_ai;
    JTextField massage;
    JButton enter;
    SimpleBot sbot;

    public static void main(String[] args) {
        new SimpleChatBot();
    }

    SimpleChatBot(){
        setTitle(TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(LOCATION,LOCATION,WIDTH,HEIGHT);

        dialog = new JTextArea();
        // перенос строк
        dialog.setLineWrap(true);
        // вертикальный скролинг
        JScrollPane scrollPane = new JScrollPane(dialog);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        bot_ai = new JCheckBox("Bot AI");
        bot_ai.doClick();
        massage = new JTextField();
        massage.addActionListener(this);
        enter = new JButton("Enter");
        enter.addActionListener(this);
        panel.add(bot_ai);
        panel.add(massage);
        panel.add(enter);
        add(BorderLayout.CENTER,scrollPane);
        add(BorderLayout.SOUTH,panel);

        setVisible(true);
        // создадим бота
        sbot = new SimpleBot();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // trim удаляет пробелы в начале и конце строки
        if (massage.getText().trim().length() > 0) {
            try {
                dialog.append(massage.getText() + "\n");
                dialog.append(TITLE.substring(0,9) + sbot.sayInReturn(massage.getText(),bot_ai.isSelected()) + "\n");
            } catch (Exception e) { }
        }
        massage.setText("");
        massage.requestFocusInWindow();
    }
}
