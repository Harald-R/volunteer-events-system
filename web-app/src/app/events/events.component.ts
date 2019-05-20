import {Component, Inject, Input, OnInit} from '@angular/core';
import {IEvent} from '@app/_models/event';
import {EventsService} from '@app/events.service';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent implements OnInit {
  @Input()
  private events: Array<IEvent>;
  private newEvent: IEvent;

  constructor(@Inject(EventsService) private eventService: EventsService) {
    eventService.getEvents().subscribe(events => this.events = events);
    this.newEvent = {};
  }

  public onSubmit() {
    this.newEvent.createdOn = new Date().getMilliseconds();
    this.eventService.addEvent(this.newEvent).subscribe((newEvent) => {
      console.log(newEvent);
      this.newEvent = newEvent; } );
    this.newEvent = {};

  }
  ngOnInit() {
  }

}
