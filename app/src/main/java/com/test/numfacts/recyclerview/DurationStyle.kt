package com.test.numfacts.recyclerview

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

class DurationStyle (private var spaceBottom: Int) : RecyclerView.ItemDecoration() {



    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        outRect.bottom = spaceBottom

    }
}