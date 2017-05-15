package com.example.seko.mwaslaty.android.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.seko.mwaslaty.android.R;
import com.example.seko.mwaslaty.android.cells.SolutionCell;
import com.example.seko.mwaslaty.android.model.Solution;

import java.util.List;

/**
 * Created by Helal on 14/05/2017.
 */
public class SolutionsAdapter extends ArrayAdapter<Solution> {
    private final Activity activity;
    private final List<Solution> solutionList;

    public SolutionsAdapter(Activity activity, int resource, List<Solution> solutionList) {
        super(activity, resource, solutionList);
        this.activity = activity;
        this.solutionList = solutionList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        SolutionCell holder;
        if (null == row) {
            row = ((Activity) (activity)).getLayoutInflater().inflate(
                    R.layout.item_list_solution, parent, false);
            holder = new SolutionCell();

            holder.setTvSolutionName((TextView) row.findViewById(R.id.tvSolutionName));
            holder.setTvSolutionCost((TextView) row.findViewById(R.id.tvSolutionCost));
            holder.setTvChangeTransportationNum((TextView) row.findViewById(R.id.tvNumTransport));

            row.setTag(holder);
        }
        holder = (SolutionCell) row.getTag();
        Solution solution = solutionList.get(position);
        if (position == 0)
            holder.getTvSolutionName().setText(solution.getTybe());
        else if (position == 1)
            holder.getTvSolutionName().setText(solution.getTybe());
        holder.getTvSolutionCost().setText(solution.getCost() + "  EGP");
        holder.getTvChangeTransportationNum().setText(String.valueOf(solution.getCheckpoints().size() - 1));
        return row;
    }

}

