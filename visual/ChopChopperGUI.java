package crChopChopper.visual;

import crChopChopper.task.Drop;
import crChopChopper.task.Interface;
import crChopChopper.task.Task;
import crChopChopper.task.antiban.Antiban;
import crChopChopper.task.bank.CloseBank;
import crChopChopper.task.bank.Deposite;
import crChopChopper.task.bank.GoBank;
import crChopChopper.task.bank.OpenBank;
import crChopChopper.task.chop.ChopTree;
import crChopChopper.task.chop.GoTree;
import crChopChopper.task.firemaking.AddLogs;
import crChopChopper.task.firemaking.LightFire;
import crChopChopper.var.Variables;
import crChopChopper.var.enums.Skill;
import crChopChopper.var.enums.Tree;
import org.powerbot.script.rt6.ClientContext;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Dakota on 10/22/2014.
 */
public class ChopChopperGUI extends JFrame {
    public static final java.util.List<Task> taskList = new ArrayList<Task>();
    private final JRadioButton dropBtn = new JRadioButton("Drop Logs");
    private final JRadioButton burnBtn = new JRadioButton("Burn Logs");
    private final JRadioButton bankBtn = new JRadioButton("Bank Logs");
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private final JButton startBtn = new JButton("Start");
    private final JPanel rootPane;

    public ChopChopperGUI(final ClientContext ctx) {
        setTitle("GUI");
        setBounds(100, 100, 200, 200);
        rootPane = new JPanel();
        rootPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(rootPane);

        JPanel panel = new JPanel();

        GroupLayout gl_rootPane = new GroupLayout(rootPane);
        gl_rootPane.setHorizontalGroup(gl_rootPane.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
                gl_rootPane.createSequentialGroup().addContainerGap().addComponent(panel, GroupLayout.DEFAULT_SIZE, 353,
                        Short.MAX_VALUE).addContainerGap()));
        gl_rootPane.setVerticalGroup(gl_rootPane.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
                        gl_rootPane.createSequentialGroup().addContainerGap().addComponent(panel, GroupLayout.DEFAULT_SIZE,
                                382, Short.MAX_VALUE).addContainerGap())
        );
        panel.setLayout(new GridLayout(0, 1, 0, 0));

        JComboBox<Tree> tree = new JComboBox<Tree>(Tree.values()) {{
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    final Tree selected = (Tree) getSelectedItem();
                    Variables.selectedTreeID = selected.getId();
                    Variables.selectedTreeName = selected.toString();
                    if (selected.toString().equals(Tree.NONE.toString())) {
                        startBtn.setEnabled(false);
                    } else {
                        startBtn.setEnabled(true);
                    }
                }
            });
        }};

        // TREE SELECTION \\
        tree.setSelectedIndex(0);
        panel.add(tree);

        // DROP BUTTON \\
        buttonGroup.add(dropBtn);
        panel.add(dropBtn);
        dropBtn.setSelected(true);

        // BURN BUTTON \\
        buttonGroup.add(burnBtn);
        panel.add(burnBtn);


        // BANK BUTTON \\
        buttonGroup.add(bankBtn);
        panel.add(bankBtn);

        // START BUTTON \\
        panel.add(startBtn);
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScriptPaint.startTime = System.currentTimeMillis();

                Formatting.startLvlWc = ctx.skills.level(Skill.WOODCUTTING.getSkillID());
                Formatting.startLvlFm = ctx.skills.level(Skill.FIREMAKING.getSkillID());

                Formatting.startXpWc = ctx.skills.experience(Skill.WOODCUTTING.getSkillID());
                Formatting.startXpFm = ctx.skills.experience(Skill.FIREMAKING.getSkillID());


                if (dropBtn.isSelected()) {
                    Variables.dropLogs = true;
                    Variables.active = true;
                }
                if (bankBtn.isSelected()) {
                    Variables.bankLogs = true;
                    Variables.active = true;
                }
                if (burnBtn.isSelected()) {
                    Variables.burnLogs = true;
                    Variables.active = true;
                }


                if (Variables.dropLogs) {
                    taskList.add(new Drop(ctx));
                    ScriptPaint.mode = "DROP";
                } else if (Variables.burnLogs) {
                    taskList.addAll(Arrays.asList(new LightFire(ctx), new AddLogs(ctx)));
                    ScriptPaint.mode = "BURN";
                } else {
                    taskList.addAll(Arrays.asList(new GoBank(ctx), new OpenBank(ctx), new Deposite(ctx), new CloseBank(ctx)));
                    ScriptPaint.mode = "BANK";
                }
                taskList.addAll(Arrays.asList(new ChopTree(ctx), new GoTree(ctx), new Interface(ctx), new Antiban(ctx)));

                dispose();
            }
        });
        rootPane.setLayout(gl_rootPane);
    }
}
