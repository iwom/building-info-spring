import {Injectable} from '@angular/core';
import {Building} from '../building';
import {HttpClient} from '@angular/common/http';
import {AppEndpoints} from '../app.endpoints';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class BuildingService {

  constructor(private httpClient: HttpClient,
              private appEndpoints: AppEndpoints) {
  }

  getBuildings(): Observable<Building[]> {
    return this.httpClient.get<Building[]>(this.appEndpoints.go().buildings(), {observe: 'body'});
  }

  getBuilding(id: number): Observable<Building> {
    return this.httpClient.get<Building>(this.appEndpoints.go().building(id), {observe: 'body'});
  }
}
