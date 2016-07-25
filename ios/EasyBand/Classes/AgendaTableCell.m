//
//  AgendaTableCell.m
//  EasyBand
//
//  Created by Eduardo Carminati on 22/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "AgendaTableCell.h"
#import "Agenda.h"

@implementation AgendaTableCell
@synthesize dayLabel,cityLabel,monthLabel,addressLabel,itemID;
- (id)initWithFrame:(CGRect)frame reuseIdentifier:(NSString *)reuseIdentifier {
	if (self = [super initWithFrame:frame reuseIdentifier:reuseIdentifier]) {
		self.itemID = 0;
		
		UIView *myContentView = self.contentView;
		
		self.cityLabel = [self newLabelWithPrimaryColor:[UIColor blackColor] selectedColor:[UIColor whiteColor] fontSize:17.0 bold:YES]; 
		self.addressLabel = [self newLabelWithPrimaryColor:[UIColor blackColor] selectedColor:[UIColor whiteColor] fontSize:13.0 bold:NO]; 
		[myContentView addSubview:self.cityLabel];
		[myContentView addSubview:self.addressLabel];


		
		int calW = 40;
		int calH = 40;
		int monthH = 16;
		int space = 8;
		UIView *calView = [[UIImageView alloc] initWithImage:[UIImage imageNamed: @"calendarBrasilia.png"]];
		calView.frame = CGRectMake( 5, 5,calW,calH);

		monthLabel = [[UILabel alloc] initWithFrame: CGRectMake(0, 0, calW, monthH)];
		monthLabel.backgroundColor = [UIColor clearColor];
		monthLabel.font = [UIFont systemFontOfSize: 12];
		monthLabel.textAlignment = UITextAlignmentCenter;
		[calView addSubview: monthLabel];
		[monthLabel release];
		dayLabel = [[UILabel alloc] initWithFrame: CGRectMake(0, monthH, calW, calH - monthH)];
		dayLabel.backgroundColor = [UIColor clearColor];
		dayLabel.font = [UIFont boldSystemFontOfSize: 20];
		dayLabel.textAlignment = UITextAlignmentCenter;
		[calView addSubview: dayLabel];
		[dayLabel release];
		
		self.cityLabel.frame = CGRectMake(calW+space*2, 0, 320 - calW+5+5 - 20 , 25);
		self.addressLabel.frame = CGRectMake(calW+space*2, space+12+space/2, 320 - calW+space*2 - 20 , 25);
		
		[self.cityLabel release];
		[self.addressLabel release];
		
		[myContentView addSubview: calView];
		[calView release];
	}
	
	return self;
}


- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
	[super setSelected:selected animated:animated];
}


- (void) setData: (Agenda *) agenda {
	self.addressLabel.text = agenda.address;
	self.cityLabel.text = agenda.city;
	NSArray *months = [NSArray arrayWithObjects: @"JAN", @"FEV", @"MAR", @"ABR", @"MAI", @"JUN", @"JUL", @"AGO", @"SET", @"OUT", @"NOV", @"DEZ", nil];
	
	self.monthLabel.text = [months objectAtIndex: [ agenda.month intValue] -1];
	
	self.dayLabel.text = agenda.day;
}


- (void)layoutSubviews {
	[super layoutSubviews];
	
    if (!self.editing) {
        CGFloat boundsX = 10;
		CGRect frame;
		
		float imgWH = 50;
        float lblx = imgWH + 2*boundsX;
		frame = CGRectMake(lblx, 5, 320 - lblx - 30,imgWH);
	//	self.cityLabel.frame = frame;
		frame = CGRectMake(boundsX + 10, 28, 200, 14);
		//self.addressLabel.frame = frame;
	}
}


- (UILabel *)newLabelWithPrimaryColor:(UIColor *)primaryColor selectedColor:(UIColor *)selectedColor fontSize:(CGFloat)fontSize bold:(BOOL)bold
{
    UIFont *font;
    if (bold) {
        font = [UIFont boldSystemFontOfSize:fontSize];
    } else {
        font = [UIFont systemFontOfSize:fontSize];
    }
    
	UILabel *newLabel = [[UILabel alloc] initWithFrame:CGRectMake(0, 0, 320, 10)];
	newLabel.backgroundColor = [UIColor whiteColor];
	newLabel.opaque = YES;
	newLabel.textColor = primaryColor;
	newLabel.highlightedTextColor = selectedColor;
	newLabel.font = font;
	
	return newLabel;
}


- (void)dealloc {
	[super dealloc];
}


@end