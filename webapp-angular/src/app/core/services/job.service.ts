import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Job } from '../entities/Job';

@Injectable({
  providedIn: 'root'
})
export class JobService {
  private JOBS_URL = 'http://localhost:8081/scheduling/jobs'

  constructor(private http: HttpClient) { }

  getAllJobs() : Observable<Job[]> {
    return this.http.get<Job[]>(this.JOBS_URL);
  }

  addJob(job: Job) : Observable<Job> {
    return this.http.post<Job>(this.JOBS_URL, job)
  }

  updateJob(job: Job) : Observable<Job> {
    return this.http.put<Job>(this.JOBS_URL + "/" + job.id, job)
  }

  deleteJob(id: number) : Observable<Object> {
    return this.http.delete(this.JOBS_URL + "/" + id)
  }
}
