package com.topdesk.cases.toprob.yoursolution;

import java.util.List;

import com.topdesk.cases.toprob.Grid;
import com.topdesk.cases.toprob.Instruction;
import com.topdesk.cases.toprob.Solution;

public class YourSolution implements Solution {
    @Override
    public List<Instruction> solve(Grid grid, int time) {
        if (grid == null) throw new NullPointerException("The Grid is must be not null.");
        if (time < 0) throw new IllegalArgumentException("The time must be more than 0 second. : " + time);

        return null;
    }
}
