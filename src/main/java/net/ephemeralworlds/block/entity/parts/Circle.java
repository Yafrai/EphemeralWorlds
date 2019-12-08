package net.ephemeralworlds.block.entity.parts;


//public class Circle implements ISerializablePart {
//
//    public EnumGlyph glyph;
//    public EnumColor color;
//
//    public Circle(EnumGlyph glyph, EnumColor color) {
//        this.glyph = glyph;
//        this.color = color;
//    }
//
//    public Circle(CompoundTag tag, String suffix) {
//        fromTag(tag, suffix);
//    }
//
//    public boolean hasGlyph() {
//        return glyph != null;
//    }
//
//    public void setGlyph(EnumGlyph glyph) {
//        this.glyph = glyph;
//    }
//
//    @Override
//    public String getName() {
//        return "circle";
//    }
//
//    @Override
//    public CompoundTag toTag(CompoundTag tag, String suffix) {
//        tag.putInt("glyph", EnumGlyph.indexOf(glyph));
//        tag.putInt("color", EnumColor.indexOf(color));
//
//        return tag;
//    }
//
//    @Override
//    public void fromTag(CompoundTag tag, String suffix) {
//        glyph = EnumGlyph.byIndex(tag.getInt("glyph"));
//        color = EnumColor.byIndex(tag.getInt("color"));
//    }
//
//    @Override
//    public void drop() {
//
//    }
//}
