package com.example.testappofferwall.Utils;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.testappofferwall.R;
import com.example.testappofferwall.game.GameView;

public class SlotScrolling extends FrameLayout {

    private static int ANIMATION = 150;

    Integer last_value = 0, first_value = 0;

    ImageView current_item, next_item;
    GameView gameView;

    private void initSlots (Context context) {
        LayoutInflater.from(context).inflate(R.layout.one_slot_scroll, this);
        current_item = getRootView().findViewById(R.id.current_item);
        next_item = getRootView().findViewById(R.id.next_item);

        next_item.setTranslationY(getHeight());
    }

    public void randomValue(final int item, final int spin_count) {
        current_item.animate().translationY(-getHeight()).setDuration(ANIMATION).start();
        next_item.setTranslationY(next_item.getHeight());
        next_item.animate().translationY(0).setDuration(ANIMATION).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                //none
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setItem(current_item, first_value % 9);
                current_item.setTranslationY(0);
                if (first_value != spin_count) {
                    randomValue(item, spin_count);
                    first_value++;
                }
                else {
                    last_value = 0;
                    first_value = 0;
                    setItem(next_item, item);
                    gameView.spinEnd(item % 9, spin_count);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                //none
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                //none
            }
        });
    }


    private void setItem(ImageView current_item, int i) {
        if (i == valueSlot.BELL) {
            current_item.setImageResource(R.drawable.slot_bell);
        }
        else if (i == valueSlot.CHERRY) {
            current_item.setImageResource(R.drawable.slot_cherry);
        }
        else if (i == valueSlot.CLOVER) {
            current_item.setImageResource(R.drawable.slot_clover);
        }
        else if (i == valueSlot.CROWN) {
            current_item.setImageResource(R.drawable.slot_crown);
        }
        else if (i == valueSlot.GRAPE) {
            current_item.setImageResource(R.drawable.slot_grape);
        }
        else if (i == valueSlot.HORSESHOE) {
            current_item.setImageResource(R.drawable.slot_horseshoe);
        }
        else if (i == valueSlot.PLUM) {
            current_item.setImageResource(R.drawable.slot_plum);
        }
        else if (i == valueSlot.SEVEN) {
            current_item.setImageResource(R.drawable.slot_seven);
        }
        else if (i == valueSlot.WATERMELON){
            current_item.setImageResource(R.drawable.slot_watermelon);
        }

        current_item.setTag(i);
        last_value = i;
    }

    public int getValue() {
        return Integer.parseInt(next_item.getTag().toString());
    }

    public SlotScrolling( Context context) {
        super(context);
        initSlots(context);
    }

    public SlotScrolling( Context context, AttributeSet attrs) {
        super(context, attrs);
        initSlots(context);
    }

    public void setSlotEndInt(GameView gameView) {
        this.gameView = gameView;
    }
}
