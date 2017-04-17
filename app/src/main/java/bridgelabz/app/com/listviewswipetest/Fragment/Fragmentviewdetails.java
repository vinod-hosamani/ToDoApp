package bridgelabz.app.com.listviewswipetest.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bridgelabz.app.com.listviewswipetest.Database.Database;
import bridgelabz.app.com.listviewswipetest.R;
import bridgelabz.app.com.listviewswipetest.model.DataModel;
import bridgelabz.app.com.listviewswipetest.ui.TodoHomeActivity;

public class Fragmentviewdetails extends Fragment implements View.OnClickListener
{
    AppCompatEditText fraglistview_EdittextView;
    AppCompatEditText fraglistviews_EdittextView;
    AppCompatButton btn;
    TodoHomeActivity todoHomeActivity=new TodoHomeActivity();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.fragment_listviewdetails, container, false);
        fraglistview_EdittextView = (AppCompatEditText)view.findViewById(R.id.fraglistview_EdittextView);
        fraglistviews_EdittextView = (AppCompatEditText)view.findViewById(R.id.fraglistviews_EdittextView);
        btn=(AppCompatButton)view.findViewById(R.id.buttonSavingg);

        Bundle bundle = getArguments();
        if (bundle != null)
        {
            String data = (bundle.getString("text"));
            String data2 = bundle.getString("text1");
            fraglistview_EdittextView.setText(data);
            fraglistviews_EdittextView.setText(data2);
        }
        return view;

    }


    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.buttonSavingg:
                DataModel datamodel=new DataModel(fraglistview_EdittextView.getText().toString(),fraglistviews_EdittextView.getText().toString());
                Database db=new Database(getActivity());
                db.updateItem(datamodel);
                todoHomeActivity.setBackData(datamodel);
                getActivity().getFragmentManager().popBackStackImmediate();
                break;

        }
    }
}
