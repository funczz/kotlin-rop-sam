package io.kotlintest.provided.com.github.funczz.kotlin.rop_sam.foo.core

interface IFooSamState {

    fun isBlank(model: FooSamModel): Boolean {
        return model.model.value.isBlank()
    }

}