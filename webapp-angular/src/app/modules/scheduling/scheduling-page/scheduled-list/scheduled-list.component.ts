import { Component, Input, OnInit, Output, SimpleChange, SimpleChanges, EventEmitter } from '@angular/core';
import { Job } from 'src/app/core/entities/Job';

@Component({
  selector: 'app-scheduled-list',
  templateUrl: './scheduled-list.component.html',
  styleUrls: ['./scheduled-list.component.css']
})
export class ScheduledListComponent implements OnInit {

  @Input() jobs: Job[];
  @Output() editJob = new EventEmitter<Job>()
  @Output() deleteJob = new EventEmitter<number>()

  constructor() {
  }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges) {
    console.log(changes)
  }

  update(job: Job) {
    this.editJob.emit(job)
  }

  delete(id: number) {
    this.deleteJob.emit(id)
  }

}
