import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppEndpoints} from '../app.endpoints';
import {Observable} from 'rxjs/Observable';
import {Floor} from '../floor';

@Injectable()
export class FloorService {

  constructor(private httpClient: HttpClient,
              private appEndpoints: AppEndpoints) {
  }

  getFloors(buildingId: number): Observable<Floor[]> {
    return this.httpClient.get<Floor[]>(this.appEndpoints.go().floors(buildingId), {observe: 'body'});
  }

  getFloor(buildingId: number, floorId: number): Observable<Floor> {
    return this.httpClient.get<Floor>(this.appEndpoints.go().floor(buildingId, floorId), {observe: 'body'});
  }

  createFloor(buildingId: number, floorData: any): Observable<Floor> {
    return this.httpClient.post<Floor>(this.appEndpoints.go().floors(buildingId), floorData, {responseType: 'json'});
  }
}
