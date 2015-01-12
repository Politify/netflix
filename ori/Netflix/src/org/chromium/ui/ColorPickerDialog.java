// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package org.chromium.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// Referenced classes of package org.chromium.ui:
//			OnColorChangedListener, ColorPickerAdvanced, ColorPickerSimple

public class ColorPickerDialog extends AlertDialog
	implements OnColorChangedListener
{

	private final ColorPickerAdvanced mAdvancedColorPicker;
	private int mCurrentColor;
	private final View mCurrentColorView;
	private final int mInitialColor;
	private final OnColorChangedListener mListener;
	private final Button mMoreButton;
	private final ColorPickerSimple mSimpleColorPicker;

	public ColorPickerDialog(Context context, OnColorChangedListener oncolorchangedlistener, int i)
	{
		super(context, 0);
		mListener = oncolorchangedlistener;
		mInitialColor = i;
		mCurrentColor = mInitialColor;
		LayoutInflater layoutinflater = (LayoutInflater)context.getSystemService("layout_inflater");
		View view = layoutinflater.inflate(R.layout.color_picker_dialog_title, null);
		setCustomTitle(view);
		mCurrentColorView = view.findViewById(R.id.selected_color_view);
		((TextView)view.findViewById(R.id.title)).setText(R.string.color_picker_dialog_title);
		setButton(-1, context.getString(R.string.color_picker_button_set), new android.content.DialogInterface.OnClickListener() {

			final ColorPickerDialog this$0;

			public void onClick(DialogInterface dialoginterface, int j)
			{
				tryNotifyColorSet(mCurrentColor);
			}

			
			{
				this$0 = ColorPickerDialog.this;
				super();
			}
		});
		setButton(-2, context.getString(R.string.color_picker_button_cancel), new android.content.DialogInterface.OnClickListener() {

			final ColorPickerDialog this$0;

			public void onClick(DialogInterface dialoginterface, int j)
			{
				tryNotifyColorSet(mInitialColor);
			}

			
			{
				this$0 = ColorPickerDialog.this;
				super();
			}
		});
		setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

			final ColorPickerDialog this$0;

			public void onCancel(DialogInterface dialoginterface)
			{
				tryNotifyColorSet(mInitialColor);
			}

			
			{
				this$0 = ColorPickerDialog.this;
				super();
			}
		});
		View view1 = layoutinflater.inflate(R.layout.color_picker_dialog_content, null);
		setView(view1);
		mMoreButton = (Button)view1.findViewById(R.id.more_colors_button);
		mMoreButton.setOnClickListener(new android.view.View.OnClickListener() {

			final ColorPickerDialog this$0;

			public void onClick(View view2)
			{
				showAdvancedView();
			}

			
			{
				this$0 = ColorPickerDialog.this;
				super();
			}
		});
		mAdvancedColorPicker = (ColorPickerAdvanced)view1.findViewById(R.id.color_picker_advanced);
		mAdvancedColorPicker.setVisibility(8);
		mSimpleColorPicker = (ColorPickerSimple)view1.findViewById(R.id.color_picker_simple);
		mSimpleColorPicker.init(this);
		updateCurrentColor(mInitialColor);
	}

	private void showAdvancedView()
	{
		findViewById(R.id.more_colors_button_border).setVisibility(8);
		findViewById(R.id.color_picker_simple_border).setVisibility(8);
		mAdvancedColorPicker.setVisibility(0);
		mAdvancedColorPicker.setListener(this);
		mAdvancedColorPicker.setColor(mCurrentColor);
	}

	private void tryNotifyColorSet(int i)
	{
		if (mListener != null)
			mListener.onColorChanged(i);
	}

	private void updateCurrentColor(int i)
	{
		mCurrentColor = i;
		if (mCurrentColorView != null)
			mCurrentColorView.setBackgroundColor(i);
	}

	public void onColorChanged(int i)
	{
		updateCurrentColor(i);
	}




}
