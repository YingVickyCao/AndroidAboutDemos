# DatePickerDialog_TimePickerDialog
- `DateTimePickerDialogFragment.java`
- `TimePickerDialogFragment.java`

## METHOD

### DatePickerDialog
```
Calendar c = Calendar.getInstance();
        new DatePickerDialog(getUsedContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker dp, int year, int month, int dayOfMonth) {
                        show.setText(year + "年" + (month + 1) + "月" + dayOfMonth + "日");
                    }
                }
                , c.get(Calendar.YEAR)
                , c.get(Calendar.MONTH)
                , c.get(Calendar.DAY_OF_MONTH))
                .show();
                
```


### TimePickerDialog
```
 new TimePickerDialog(getUsedContext(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int hourOfDay, int minute) {
                        show.setText(hourOfDay + "时" + minute + "分");
                    }
                }
                , c.get(Calendar.HOUR_OF_DAY)
                , c.get(Calendar.MINUTE)
                //true表示采用24小时制
                , true).show();
```