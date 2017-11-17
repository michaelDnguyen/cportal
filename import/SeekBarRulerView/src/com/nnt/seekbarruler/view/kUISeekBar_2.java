package com.nnt.seekbarruler.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.nnt.seekbarrulerview.R;


/**
 * Created by khavn on 6/5/15.
 */


public class kUISeekBar_2 extends View {


    public interface kUISeekBarDelegate {
        void kUISeekBarOnChangeValue(kUISeekBar_2 view,int value);
    }

    private boolean debug = true;

    // PRIVATE VARIABLEs
    private int _minValue;
    private int _maxValue;
    private float _currentValue;

    private float _minDisplay;
    private float _maxDisplay;

    private int _unitStep; // number step display

    private int _leftEdge;
    private int _rightEdge;

    // variables for render
    private Paint _paint;

    private int _rulerColor;
    private String _rulerUnit;
    private boolean _rulerIsLine;

    private int _textColor;
    private float _textSize;
    private int _sliderColor;
    private int _cursorColor;
    private int _borderColor;

    private int _sliderHeight;

    private Bitmap _cursorImage;


    private int _verticalPadding;
    private int _horizonPadding;

    private int _cursorRadius;
    private float _unitWidth;


    private kUISeekBarDelegate _delegate;
    // end PRIVATE VARIABLEs


    // timer
    private boolean _isAnimation = false;
    private float _deltaStep = 0;
    private int _deltaTime = 200;
    private Handler _hander;
    private Runnable _updateTimerThread = new Runnable() {
        @Override
        public void run() {
            float temp;

            if (_deltaStep < 0) {
                // down
                if (_minDisplay + _deltaStep < _minValue)
                    _deltaStep = _minValue - _minDisplay;
            } else if (_deltaStep > 0) {
                // up
                if (_maxDisplay + _deltaStep > _maxValue)
                    _deltaStep = _maxValue - _maxDisplay;
            }


            _minDisplay += _deltaStep;
            _maxDisplay += _deltaStep;
            _currentValue += _deltaStep;

            if (_delegate != null)
                _delegate.kUISeekBarOnChangeValue(kUISeekBar_2.this, (int) _currentValue);

            reloadView();


            if (_currentValue == _minValue || _currentValue == _maxValue)
                stopAnimatin();
            else
                _hander.postDelayed(this, _deltaTime);
        }
    };
    ;
    // end timer

    //Log
    public void Log(String mess) {
        if (debug)
            Log.d("UISeekBar", mess);
    }

    // PROPERTIES

    // minValue
    public int getMinValue() {
        return _minValue;
    }

    public void setMinValue(int value) {
        if (_minValue != value) {
            _minValue = value;

            if (_minDisplay < value)
                _minDisplay = value;

            if (_maxValue < value)
                _maxValue = value;

            if (_currentValue < value)
                _currentValue = value;

            this.reloadView();
        }
    }

    // maxValue
    public int getMaxValue() {
        return _maxValue;
    }

    public void setMaxValue(int value) {
        if (_maxValue != value) {
            _maxValue = value;

            if (_maxDisplay > value)
                _maxDisplay = value;

            if (_minDisplay > value)
                _minValue = value;

            if (_currentValue > value)
                _currentValue = value;

            this.reloadView();
        }
    }

    // currentValue
    public int getCurrentValue() {
        return (int) _currentValue;
    }

    public void setCurrentValue(float value) {
        if (value < _minValue) value = _minValue;
        else if (value > _maxValue) value = _maxValue;

        if (value < _minDisplay) {
            _maxDisplay = _maxDisplay - (_minDisplay - value);
            _minDisplay = value;
        } else if (value > _maxDisplay) {
            _minDisplay = _minDisplay + (value - _maxDisplay);
            _maxDisplay = value;
        }


        if (_currentValue != value) {
            _currentValue = value;
            float x = convertValueToX(value);
            Log(" X =  " + x + " ~ value = " + value);
            this.moveToX(x);
            this.reloadView();
        }
    }

    public void setCurentValue(int value) {
        if (value < _minValue) value = _minValue;
        else if (value > _maxValue) value = _maxValue;

        if (value < _minDisplay) {
            _maxDisplay = _maxDisplay - (_minDisplay - value);
            _minDisplay = value;
        } else if (value > _maxDisplay) {
            _minDisplay = _minDisplay - (value - _maxDisplay);
            _maxDisplay = value;
        }

        if (_currentValue != value) {
            _currentValue = value;
            this.reloadView();
        }
    }

    // minDisplay
    public float getMinDisplay() {
        return _minDisplay;
    }

    public void setMinDisplay(float value) {
        if (_minDisplay != value) {
            _minDisplay = value;

            if (_minValue > value)
                _minValue = (int) value;

            if (_currentValue < value)
                _currentValue = value;

            this.reloadView();
        }
    }

    // maxDisplay
    public float getMaxDisplay() {
        return _maxDisplay;
    }

    public void setMaxDisplay(float value) {
        if (_maxDisplay != value) {
            _maxDisplay = value;

            if (_maxValue < value)
                _maxValue = (int) value;

            if (_currentValue > value)
                _currentValue = value;

            this.reloadView();
        }
    }

    // unitStep
    public int getUnitStep() {
        return _unitStep;
    }

    public void setUnitStep(int value) {
        if (_unitStep != value) {
            _unitStep = value;
            this.reloadView();
        }
    }

    // textColor
    public int getTextColor() {
        return _textColor;
    }

    public void setTextColor(int value) {
        _textColor = value;

        this.reloadView();
    }

    //text size
    public float getTextSize() {
        return _textSize;
    }

    public void setTextSize(float value) {
        _textSize = value;

        this.reloadView();
    }

    // rulerColor
    public int getRulerColor() {
        return _rulerColor;
    }

    public void setRulerColor(int value) {
        _rulerColor = value;

        this.reloadView();
    }

    //ruler is Line
    public void setRulerIsLine(boolean value){
        _rulerIsLine = value;
        this.reloadView();
    }

    public boolean rulerIsLine(){
        return _rulerIsLine;
    }

    // rulerUnit
    public String getRulerUnit() {
        return _rulerUnit;
    }

    public void setRulerUnit(String value) {
        _rulerUnit = value;

        this.reloadView();
    }

    // sliderColor
    public int getSliderColor() {
        return _sliderColor;
    }

    public void setSliderColor(int value) {
        _sliderColor = value;

        this.reloadView();
    }

    // cursorColor
    public int getCursorColor() {
        return _cursorColor;
    }

    public void setCursorColor(int value) {
        _cursorColor = value;

        this.reloadView();
    }

    // cursorImage
    public void setCursorImage(Drawable dw) {
        this.loadCursorImage(dw);
        this.reloadView();
        // scale;
    }

    // borderColor
    public int getBorderColor() {
        return _borderColor;
    }

    public void setBorderColor(int value) {
        _borderColor = value;
    }

    // sliderHeight
    public int getSliderHeight() {
        return _sliderHeight;
    }

    public void setSliderHeight(int value) {
        _sliderHeight = value;

        this.reloadView();
    }

    // delegate
    public kUISeekBarDelegate getDelegate() {
        return _delegate;
    }

    public void setDelegate(kUISeekBarDelegate value) {
        _delegate = value;
    }
    // end PROPERTIES


    // CONTRUCTORs
    public kUISeekBar_2(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.kUISeekBar, 0, 0);

        // fill data
        try {
            float density = this.getResources().getDisplayMetrics().density;

            _minValue = a.getInt(R.styleable.kUISeekBar_minValue, 0);
            _maxValue = a.getInt(R.styleable.kUISeekBar_maxValue, 100);
            _currentValue = a.getInt(R.styleable.kUISeekBar_currentValue, 0);

            _minDisplay = a.getInt(R.styleable.kUISeekBar_minDisplay, 0);
            _maxDisplay = a.getInt(R.styleable.kUISeekBar_maxDisplay, 100);

            _unitStep = a.getInt(R.styleable.kUISeekBar_unitStep, 10);

            _rulerColor = a.getColor(R.styleable.kUISeekBar_rulerColor, Color.BLACK);
            _rulerUnit = a.getString(R.styleable.kUISeekBar_rulerUnit);
            _rulerIsLine = a.getBoolean(R.styleable.kUISeekBar_rulerIsLine, false);

            _textColor = a.getColor(R.styleable.kUISeekBar_textColor, Color.BLACK);
            _textSize = a.getDimension(R.styleable.kUISeekBar_textSize, 14);
            _sliderColor = a.getColor(R.styleable.kUISeekBar_sliderColor, Color.rgb(224, 224, 224));
            _cursorColor = a.getColor(R.styleable.kUISeekBar_cursorColor, Color.rgb(182, 9, 14));
            _borderColor = a.getColor(R.styleable.kUISeekBar_borderColor, Color.BLUE);

            _sliderHeight = (int) (a.getInt(R.styleable.kUISeekBar_sliderHeight, 10) * density); // default 10 px

            Drawable dw = a.getDrawable(R.styleable.kUISeekBar_cursorImage);
            this.loadCursorImage(dw);


            _delegate = null;
        } finally {
            a.recycle();
        }


        // setup timer
        _hander = new Handler();


        // setup render
        this.initRender();

        // re render view
        this.reloadView();
    }
    // End CONTRUCTORs


    // OVERRIDER PROTECTED
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float density = this.getResources().getDisplayMetrics().density;
        Log("DENSITY" + density);
        int width = this.getWidth();
        int height = this.getHeight();

        // get screen size
        Point size = new Point();
        Display display = (Display) ((WindowManager) this.getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= 13) {
            display.getSize(size);
        } else {
            size.x = display.getWidth();
            size.y = display.getHeight();
        }

        // hard screen size in real render
        if (size.x == 0) size.x = 10000;
        if (size.y == 0) size.y = 10000;
        // end

        if (width > size.x) width = size.x;

        _verticalPadding = (int) (9 * density);
        _horizonPadding = (int) (14 * density);
        _cursorRadius = (_sliderHeight + _verticalPadding) / 2;
        _unitWidth = ((width - 2 * (_horizonPadding + _cursorRadius)) / (float) (_maxDisplay - _minDisplay)); // 1 unit to pxel

        int temp = (int) (width * 0.15);
        _leftEdge = temp;
        _rightEdge = width - temp;


        int left, top, right, bottom;
        left = 0;

        // draw slider border
        left = _horizonPadding;
        top = _verticalPadding;
        right = width - _horizonPadding;
        bottom = top + _sliderHeight;
        _paint.setColor(_borderColor);
        _paint.setStyle(Paint.Style.FILL);
//        canvas.drawRect(left, top, right, bottom, _paint);

        //draw slider background
        temp = (int) (density);
        left += temp;
        top += temp;
        right -= temp;
        bottom -= temp;
        _paint.setColor(_sliderColor);
        _paint.setStyle(Paint.Style.FILL);
//        canvas.drawRect(left, top, right, bottom, _paint);


        // draw line ruler
        _paint.setColor(_rulerColor);
        left = _horizonPadding;
        top = 2 * _verticalPadding + _sliderHeight;
        right = width - _horizonPadding + 5;
        if(!_rulerIsLine)
            bottom = top + temp + (int)_textSize*2;
        else
            bottom = top + temp;
        Log("height of bolder = "+ bottom);
        canvas.drawRect(left, top, right, bottom, _paint);

        //draw process change
        _paint.setColor(Color.RED);
        left = _horizonPadding;
        top = 2 * _verticalPadding + _sliderHeight;
        int a,b;
        b = top + temp + (int)_textSize*2;
        if (_cursorImage == null) {
            a = (int) (_horizonPadding + _cursorRadius + (_currentValue - _minDisplay) * _unitWidth); // center x
        } else {
            a = (int) (_horizonPadding + (_currentValue - _minDisplay) * _unitWidth) + _cursorRadius; // center x
        }
        canvas.drawRect(left, top, a, b, _paint);

        // draw number
        _paint.setColor(_textColor);
        Log("TEXTSIZE" + _textSize);
        _paint.setTextSize(_textSize);
        _paint.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD,Typeface.BOLD));

        float unitWidth = _unitWidth;

        int mind = (int) (Math.floor(_minDisplay / (float) _unitStep) * _unitStep);
        int maxd = (int) (Math.ceil(_maxDisplay / (float) _unitStep) * _unitStep);

        top = 2 * _verticalPadding + _sliderHeight + temp;
        left = (int) (_cursorRadius + _horizonPadding - (_minDisplay - mind) * unitWidth);

        canvas.save();

        Rect rect = new Rect();
        canvas.clipRect(_horizonPadding, top, width - _horizonPadding, top + 60);
        unitWidth *= _unitStep;
        for (int i = mind; i <= maxd; i += _unitStep, left += unitWidth) {
            String text = Integer.toString(i);
            _paint.getTextBounds(text, 0, text.length(), rect);

            canvas.drawText(text + (_rulerUnit != null ? _rulerUnit : ""), left - rect.width() / 2, top + rect.height(), _paint);
        }
        unitWidth /= _unitStep;
        canvas.restore();


        // draw cursor seekbar
        if (_cursorImage == null) {
            left = (int) (_horizonPadding + _cursorRadius + (_currentValue - _minDisplay) * unitWidth); // center x
            top = _verticalPadding + _sliderHeight / 2; // center y
            _paint.setColor(_textColor);
            canvas.drawCircle(left, top, _cursorRadius, _paint);
            _paint.setColor(_cursorColor);
            canvas.drawCircle(left, top, _cursorRadius - temp, _paint);
        } else {
            left = (int) (_horizonPadding + (_currentValue - _minDisplay) * unitWidth); // center x
            top = _verticalPadding + _sliderHeight / 2 - _cursorRadius; // center y

            temp = 2 * _cursorRadius;
            Rect sr = new Rect(0, 0, _cursorImage.getWidth(), _cursorImage.getHeight());
            Rect dr = new Rect(left, top, left + temp, top + temp*2);
            Log.d("rkm", dr.toString());
            canvas.drawBitmap(_cursorImage, sr, dr, _paint);
        }


        // draw debug
//        _paint.setColor(Color.RED);
//        canvas.drawLine(_leftEdge, 0, _leftEdge, height, _paint);
//        canvas.drawLine(_rightEdge, 0, _rightEdge, height, _paint);
    }
    // End OVERRIDER PROTECTED


    // HANDLE TOUCH EVENT
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        float tx = event.getX();

        if (action == MotionEvent.ACTION_DOWN) {
            this.moveToX(tx);

            this.setCurrentValue(this.convertXToValue(tx));

            if (_delegate != null)
                _delegate.kUISeekBarOnChangeValue(this, (int) _currentValue);
        } else if (action == MotionEvent.ACTION_MOVE) {
            this.moveToX(tx);

            this.setCurrentValue(this.convertXToValue(tx));

            if (_delegate != null)
                _delegate.kUISeekBarOnChangeValue(this, (int) _currentValue);
        } else if (action == MotionEvent.ACTION_UP) {
            this.stopAnimatin();
        }
        return true;
    }
    // End HANDLE TOUCH EVENT


    // PRIVATE METHODs
    private void initRender() {
        _paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    private void reloadView() {
        float density = this.getResources().getDisplayMetrics().density;
        int width = this.getWidth();

        _verticalPadding = (int) (9 * density);
        _horizonPadding = (int) (14 * density);
        _cursorRadius = (_sliderHeight + _verticalPadding) / 2;
        _unitWidth = ((width - 2 * (_horizonPadding + _cursorRadius)) / (float) (_maxDisplay - _minDisplay)); // 1 unit to pxel

        int temp = (int) (width * 0.15);
        _leftEdge = temp;
        _rightEdge = width - temp;


        this.invalidate();
        this.requestLayout();
    }

    private void moveEdge(int value) {
        _minDisplay += value;
        _maxDisplay += value;
    }

    private float convertXToValue(float x) {
        x -= _horizonPadding + _cursorRadius; // correct x

        float value = x / _unitWidth + _minDisplay;

        return value;
    }
    
    private float convertValueToX(float value) {
        float x = (value - _minDisplay)*_unitWidth;
        x += _horizonPadding - _cursorRadius; // correct x 
        
        return x;
    }


    private void moveToX(float tx) {
        if (tx < _leftEdge) {
            // caculate animation
            float delta = tx - _leftEdge;
            _deltaStep = delta / _unitStep;
            this.startAnimation();
        } else if (tx > _rightEdge) {
            float delta = tx - _rightEdge;
            _deltaStep = delta / _unitStep;
            this.startAnimation();
        } else {
            this.stopAnimatin();
        }
    }


    private void startAnimation() {
        if (!_isAnimation) {
            _isAnimation = true;
            _hander.postDelayed(_updateTimerThread, _deltaTime);
        }
    }

    private void stopAnimatin() {
        if (_isAnimation) {
            _isAnimation = false;
            _hander.removeCallbacks(_updateTimerThread);
        }
    }


    private void loadCursorImage(Drawable dw) {
        if (dw == null) {
            _cursorImage = null;
        } else {
            Bitmap bmp;


            if (dw instanceof BitmapDrawable) {
                bmp = ((BitmapDrawable) dw).getBitmap();
                Log.d("rkm", "ok");
            } else {
                bmp = Bitmap.createBitmap(dw.getIntrinsicWidth(), dw.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bmp);
                dw.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                dw.draw(canvas);
            }

            // scale
            float sw, sh, ss;
            sw = (float) bmp.getWidth() / 80f;
            sh = (float) bmp.getHeight() / 80f;

            ss = Math.min(sw, sh);
            sw = (float) bmp.getWidth() / ss;
            sh = (float) bmp.getHeight() / ss;

            Log.d("rkm", sw + " " + sh);
            _cursorImage = Bitmap.createScaledBitmap(bmp, (int) sw, (int) sh, false);
        }
    }
    // End PRIVATE METHODs
}
