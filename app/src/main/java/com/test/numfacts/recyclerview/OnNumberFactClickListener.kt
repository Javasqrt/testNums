package com.test.numfacts.recyclerview

import com.test.numfacts.db.FactViewElementEntity

interface OnNumberFactClickListener {

    fun onClick(positionList: Int, factView: FactViewElementEntity)
}