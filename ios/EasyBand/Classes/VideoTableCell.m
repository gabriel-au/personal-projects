//
//  VideoTableCell.m
//  EasyBand
//
//  Created by Eduardo Carminati on 09/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "VideoTableCell.h"


@implementation VideoTableCell

// we need to synthesize the two labels
@synthesize titleLabel, urlLabel;
@synthesize itemID;
@synthesize imageView;

- (id)initWithFrame:(CGRect)frame reuseIdentifier:(NSString *)reuseIdentifier {
	if (self = [super initWithFrame:frame reuseIdentifier:reuseIdentifier]) {
		self.itemID = 0;

		UIView *myContentView = self.contentView;
		self.titleLabel = [self newLabelWithPrimaryColor:[UIColor blackColor] selectedColor:[UIColor whiteColor] fontSize:12.0 bold:YES]; 

		UIImage *noPicImageSmall = [[UIImage imageNamed:@"nopic-small.png"] retain];
		
		self.imageView = [[UIImageView alloc] initWithFrame: CGRectMake(10,5,50,30)];
		imageView.image = noPicImageSmall;
		
		[myContentView addSubview:self.titleLabel];
		[myContentView addSubview:self.imageView];
		[self.imageView release];
		[self.titleLabel release];
	}
	
	return self;
}


- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
	
	[super setSelected:selected animated:animated];
	
	// Configure the view for the selected state
}

/*
 this function gets in data from another area in the code
 you can see it takes a NSDictionary object
 it then will set the label text
 */
-(void)setData:(NSDictionary *)dict {
	self.titleLabel.text = [dict objectForKey:@"title"];
	self.imageView.image = [dict objectForKey:@"img"];
}

- (void)layoutSubviews {
	
    [super layoutSubviews];

    if (!self.editing) {
        CGFloat boundsX = 10;
		CGRect frame;
        
		frame = CGRectMake(140, 5, 160, 90);
		self.titleLabel.frame = frame;
		self.titleLabel.numberOfLines = 5;
		frame = CGRectMake(boundsX + 10, 28, 200, 14);
		
		self.imageView.frame = CGRectMake(boundsX, 5, 120,90);
	}
}


/*
 this function was taken from an XML example
 provided by Apple
 
 I can take no credit in this
 */
- (UILabel *)newLabelWithPrimaryColor:(UIColor *)primaryColor selectedColor:(UIColor *)selectedColor fontSize:(CGFloat)fontSize bold:(BOOL)bold
{
	/*
	 Create and configure a label.
	 */
	
    UIFont *font;
    if (bold) {
        font = [UIFont boldSystemFontOfSize:fontSize];
    } else {
        font = [UIFont systemFontOfSize:fontSize];
    }
    
    /*
	 Views are drawn most efficiently when they are opaque and do not have a clear background, so set these defaults.  To show selection properly, however, the views need to be transparent (so that the selection color shows through).  This is handled in setSelected:animated:.
	 */
	UILabel *newLabel = [[UILabel alloc] initWithFrame:CGRectMake(500, 0, 320, 10)];
	newLabel.backgroundColor = [UIColor whiteColor];
	newLabel.opaque = YES;
	newLabel.textColor = primaryColor;
	newLabel.highlightedTextColor = selectedColor;
	newLabel.font = font;
	
	return newLabel;
}


- (void)dealloc {
	// make sure you free the memory
	[titleLabel dealloc];
	//[urlLabel dealloc];
	[imageView dealloc];
	[super dealloc];
}


@end
