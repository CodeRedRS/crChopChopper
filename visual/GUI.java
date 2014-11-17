package crChopChopper.visual;

import crChopChopper.task.Drop;
import crChopChopper.task.Interface;
import crChopChopper.task.Task;
import crChopChopper.task.bank.CloseBank;
import crChopChopper.task.bank.Deposite;
import crChopChopper.task.bank.GoBank;
import crChopChopper.task.bank.OpenBank;
import crChopChopper.task.chop.ChopTree;
import crChopChopper.task.chop.GoTree;
import crChopChopper.task.firemaking.AddLogs;
import crChopChopper.task.firemaking.LightFire;
import crChopChopper.var.Variables;
import crChopChopper.var.enums.Tree;
import org.powerbot.script.rt6.ClientContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by CodeRed on 11/16/2014.
 */
public class GUI {
    public boolean guiFinished = false;
    public final java.util.List<Task> taskList = new ArrayList<Task>();
    public int METHOD;

    public GUI(final ClientContext ctx) {
        final JFrame FRAME = new JFrame("GUI");
        final JPanel PANEL = new JPanel();
        final Tree[] TREE_TYPES = Tree.values();
        final JComboBox<Tree> TREES = new JComboBox<Tree>(TREE_TYPES);
        final JComboBox<String> METHODS = new JComboBox<String>(new String[]{"Drop", "Bank", "Burn"});
        final JButton START = new JButton("Start");

        PANEL.setLayout(new BorderLayout());
        TREES.setSelectedIndex(0);
        START.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScriptPaint.status = "GUI";
                final Tree TREE = (Tree) TREES.getSelectedItem();
                int METHOD = METHODS.getSelectedIndex();
                Variables.selectedTreeID = TREE.getId();
                Variables.selectedTreeName = TREE.toString();
                taskList.addAll(Arrays.asList(new ChopTree(ctx), new GoTree(ctx), new Interface(ctx)));

                switch (METHOD) {
                    case 0:
                        taskList.add(new Drop(ctx));
                        Variables.dropLogs = true;
                        ScriptPaint.mode = "DROP";
                        break;
                    case 1:
                        taskList.addAll(Arrays.asList(new GoBank(ctx), new OpenBank(ctx), new Deposite(ctx), new CloseBank(ctx)));
                        Variables.bankLogs = true;
                        ScriptPaint.mode = "BANK";
                        break;
                    case 2:
                        taskList.addAll(Arrays.asList(new LightFire(ctx), new AddLogs(ctx)));
                        Variables.burnLogs = true;
                        ScriptPaint.mode = "BURN";
                        break;
                }
                guiFinished = true;
                FRAME.dispose();
            }
        });
        PANEL.add(TREES, BorderLayout.NORTH);
        PANEL.add(METHODS, BorderLayout.CENTER);
        PANEL.add(START, BorderLayout.SOUTH);
        FRAME.add(PANEL);
        FRAME.pack();
        FRAME.setVisible(true);
    }
}
