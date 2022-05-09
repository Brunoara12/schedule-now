import { Component, OnInit } from '@angular/core';
import { Job } from 'src/app/core/entities/Job';
import { JobService } from 'src/app/core/services/job.service';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  selector: 'app-scheduling-page',
  templateUrl: './scheduling-page.component.html',
  styleUrls: ['./scheduling-page.component.css']
})
export class SchedulingPageComponent implements OnInit {
  username: string = ""
  jobs: Job[] = [];
  toUpdate: Job ;

  constructor(private jobService: JobService, private userService: UserService) { }

  ngOnInit() {

    this.getAllJobs()
    this.username = this.userService.getUser()
  }

  getAllJobs() {
    this.jobService.getAllJobs().subscribe((jobs) => {

      this.jobs = jobs
      console.log(this.jobs)
    })
  }
  addJob(job: Job) {
    this.jobService.addJob(job).subscribe((newJob) => {
      console.log("Job Created")
      this.getAllJobs();
    })
  }

  updateJob(job: Job) {
    this.jobService.updateJob(job).subscribe((updateJob) => {
      console.log("Job Updated")
      this.getAllJobs()
    })
  }

  editJob(job: Job) {
    console.log(job)
    this.toUpdate = job;
  }

  deleteJob(id: number) {
    this.jobService.deleteJob(id).subscribe( () => {
      console.log("Job Deleted")
      this.getAllJobs()
    })
  }
}
