// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package org.chromium.content.browser.input;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

// Referenced classes of package org.chromium.content.browser.input:
//			TwoFieldDatePicker

public abstract class TwoFieldDatePickerDialog extends AlertDialog
	implements android.content.DialogInterface.OnClickListener, TwoFieldDatePicker.OnMonthOrWeekChangedListener
{
	public static interface OnValueSetListener
	{

		public abstract void onValueSet(int i, int j);
	}


	private static final String POSITION_IN_YEAR = "position_in_year";
	private static final String YEAR = "year";
	protected final OnValueSetListener mCallBack;
	protected final TwoFieldDatePicker mPicker;

	public TwoFieldDatePickerDialog(Context context, int i, OnValueSetListener onvaluesetlistener, int j, int k, long l, 
			long l1)
	{
		super(context, i);
		mCallBack = onvaluesetlistener;
		setButton(-1, context.getText(org.chromium.content.R.string.date_picker_dialog_set), this);
		setButton(-2, context.getText(0x1040000), (android.content.DialogInterface.OnClickListener)null);
		setIcon(0);
		mPicker = createPicker(context, l, l1);
		setView(mPicker);
		mPicker.init(j, k, this);
	}

	public TwoFieldDatePickerDialog(Context context, OnValueSetListener onvaluesetlistener, int i, int j, long l, long l1)
	{
		this(context, 0, onvaluesetlistener, i, j, l, l1);
	}

	protected TwoFieldDatePicker createPicker(Context context, long l, long l1)
	{
		return null;
	}

	public void onClick(DialogInterface dialoginterface, int i)
	{
		tryNotifyDateSet();
	}

	public void onMonthOrWeekChanged(TwoFieldDatePicker twofielddatepicker, int i, int j)
	{
		mPicker.init(i, j, null);
	}

	protected void onStop()
	{
		if (android.os.Build.VERSION.SDK_INT >= 16)
			tryNotifyDateSet();
		super.onStop();
	}

	protected abstract void tryNotifyDateSet();

	public void updateDate(int i, int j)
	{
		mPicker.updateDate(i, j);
	}
}
