package com.gildedrose;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class GildedRose {
    public static final String AGED_BRIE= "Aged Brie";
	public static final String BACKSTAGE_PASSES= "Backstage passes to a TAFKAL80ETC concert";
	public static final String SULFURAS= "Sulfuras, Hand of Ragnaros";
	public static final String CONJURED= "Conjured Mana Cake";
    
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
    	List<Item> listItems=Arrays.stream(items).collect( Collectors.toList());
    	listItems.forEach(item -> {
            updateItemQuality(item);
    	});
    }
    
    private void updateItemQuality(Item item) {
         boolean isExpired=item.sellIn < 1;
         if (!item.name.equals(AGED_BRIE) && !item.name.equals(BACKSTAGE_PASSES) &&  !item.name.equals(SULFURAS))
                setQuality(item,updateQuality(item,isExpired));
         
          if (item.name.equals(AGED_BRIE)) {
              int value =isExpired ? 2 : 1 ;
              setQuality(item,value);
          }
          if (item.name.equals(BACKSTAGE_PASSES))
                setBackstagepass(item,isExpired);
           
          if (!item.name.equals(SULFURAS))
                item.sellIn = item.sellIn - 1;
     }
    
    private int updateQuality(Item item, boolean isExpired) {
		int value=item.name.equals(CONJURED) ? -2 : -1;
		return isExpired? value * 2 : value;
	}
    
    private void setBackstagepass(Item item, boolean isExpired) {
		setQuality(item,1);
		if(item.sellIn < 11)
			setQuality(item,1);
		if(item.sellIn < 6)
			setQuality(item,1);
		if(isExpired)
			item.quality=item.quality-item.quality;
	}
    
    private void setQuality(Item item, int degradeRate) {
		int updatdQuality  = item.quality+degradeRate;
		boolean isvalidRange = updatdQuality <= 50 && updatdQuality >= 0;
		if(isvalidRange)
			item.quality =updatdQuality;
	}
   
}
