//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.1.
//


package agh.edu.pl.quiz.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;
import com.example.quiz.R.id;
import com.example.quiz.R.layout;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class FinishActivity_
    extends FinishActivity
    implements HasViews, OnViewChangedListener
{

    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
        setContentView(layout.activity_finish);
    }

    private void init_(Bundle savedInstanceState) {
        OnViewChangedNotifier.registerOnViewChangedListener(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    public static FinishActivity_.IntentBuilder_ intent(Context context) {
        return new FinishActivity_.IntentBuilder_(context);
    }

    public static FinishActivity_.IntentBuilder_ intent(android.app.Fragment fragment) {
        return new FinishActivity_.IntentBuilder_(fragment);
    }

    public static FinishActivity_.IntentBuilder_ intent(android.support.v4.app.Fragment supportFragment) {
        return new FinishActivity_.IntentBuilder_(supportFragment);
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        correct = ((TextView) hasViews.findViewById(id.correct));
        score = ((TextView) hasViews.findViewById(id.score));
        finishRestartButton = ((Button) hasViews.findViewById(id.finishResterButton));
        incorrect = ((TextView) hasViews.findViewById(id.incorrect));
        finishLoadButton = ((Button) hasViews.findViewById(id.finishLoadButton));
        if (finishLoadButton!= null) {
            finishLoadButton.setOnClickListener(new OnClickListener() {


                @Override
                public void onClick(View view) {
                    FinishActivity_.this.load();
                }

            }
            );
        }
        if (finishRestartButton!= null) {
            finishRestartButton.setOnClickListener(new OnClickListener() {


                @Override
                public void onClick(View view) {
                    FinishActivity_.this.restart();
                }

            }
            );
        }
        showStatistics();
    }

    public static class IntentBuilder_
        extends ActivityIntentBuilder<FinishActivity_.IntentBuilder_>
    {

        private android.app.Fragment fragment_;
        private android.support.v4.app.Fragment fragmentSupport_;

        public IntentBuilder_(Context context) {
            super(context, FinishActivity_.class);
        }

        public IntentBuilder_(android.app.Fragment fragment) {
            super(fragment.getActivity(), FinishActivity_.class);
            fragment_ = fragment;
        }

        public IntentBuilder_(android.support.v4.app.Fragment fragment) {
            super(fragment.getActivity(), FinishActivity_.class);
            fragmentSupport_ = fragment;
        }

        @Override
        public void startForResult(int requestCode) {
            if (fragmentSupport_!= null) {
                fragmentSupport_.startActivityForResult(intent, requestCode);
            } else {
                if (fragment_!= null) {
                    fragment_.startActivityForResult(intent, requestCode);
                } else {
                    super.startForResult(requestCode);
                }
            }
        }

    }

}
