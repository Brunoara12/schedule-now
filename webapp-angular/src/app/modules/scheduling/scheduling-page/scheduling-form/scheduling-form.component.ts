import { Component, Input, OnInit, Output, EventEmitter, SimpleChanges } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { Job } from 'src/app/core/entities/Job';

@Component({
  selector: 'app-scheduling-form',
  templateUrl: './scheduling-form.component.html',
  styleUrls: ['./scheduling-form.component.css']
})
export class SchedulingFormComponent implements OnInit {
  sub: Subscription;
  jobForm: FormGroup;
  username: string;
  @Input() toUpdate: Job;
  @Output() addJob = new EventEmitter<Job>()
  @Output() updateJob = new EventEmitter<Job>()

  constructor(private fb: FormBuilder) { }

  ngOnInit() {
    this.jobForm = this.fb.group({
      id: null,
      customerName: new FormControl('',[ Validators.required, Validators.minLength(3)]),
      address: '',
      email: new FormControl('', [Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]),
      phone: new FormControl('', [Validators.required]),
      dateTime: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required])
    })
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

  submit() {
    this.reset();
  }

  add(job: Job) {
    this.addJob.emit(job);
    console.log(job)
  }

  update(job: Job) {
    this.updateJob.emit(job)
    console.log(job)
  }

  reset() {
    this.jobForm.reset();
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes.toUpdate.currentValue) {
      let job = changes.toUpdate.currentValue
      console.log('Job id to be edited: ', job.id)
      this.jobForm.patchValue({
        id: job.id,
        customerName: job.customerName,
        address: job.address,
        email: job.email,
        phone: job.phone,
        dateTime: job.dateTime.slice(0,10),
        description: job.description
      })
    }
  }
}
