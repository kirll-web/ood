package Designer

import Picture.PictureDraft

interface IDesigner {
    fun createDraft(): PictureDraft
}